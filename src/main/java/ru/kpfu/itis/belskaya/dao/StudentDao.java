package ru.kpfu.itis.belskaya.dao;

import ru.kpfu.itis.belskaya.entities.Student;
import ru.kpfu.itis.belskaya.entities.Tutor;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.exceptions.EncryptionException;
import ru.kpfu.itis.belskaya.instruments.passwordHelper.Password;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements UserDao<Student> {

    private  Connection conn;

    public StudentDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Student user) throws DbException {
        int newId = getLastId(conn, "student") + 1;
        String salt = "";
        String request = "INSERT INTO students(id, name, hash, email, city, phone, salt) Values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(request)) {
            preparedStatement.setInt(1, newId);
            preparedStatement.setString(2, user.getName());
            salt = Password.generateSalt(user.getPasswordHash().length());
            preparedStatement.setString(3, Password.hashPassword(user.getPasswordHash(), salt));
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getCity());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, salt);
            preparedStatement.executeUpdate();
        } catch (SQLException | EncryptionException e) {
            throw new DbException("Error writing user to the database", e);
        }

    }

    public int findUserByPhone(String phone) throws DbException {
        return findUserByPhone(conn, phone, "student");
    }

    public int findUserByEmail(String email) throws DbException {
        return findUserByEmail(conn, email, "student");
    }

    @Override
    public Student findById(int id) throws DbException {
        String request = "SELECT * FROM students where id = (?)";
        Student user = null;
        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = getStudentFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DbException("Error getting user data from the database", e);
        }
        return user;
    }

    public List<Student> getStudentsOfThisTutor(int tutorId) throws DbException {
        String request = "select author_id from orders where tutor_id = ?";
        List<Student> tutorStudentList = new ArrayList<Student>();

        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setInt(1, tutorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Student student = findById(rs.getInt("author_id"));
                tutorStudentList.add(student);
            }
        } catch (SQLException e) {
            throw new DbException("Error getting students from the database", e);
        }

        return tutorStudentList;
    }

    @Override
    public List<Student> getAll() throws DbException {
        ArrayList<Student> userList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                Student user = getStudentFromResultSet(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DbException("Error getting data from the database", e);
        }
        return userList;
    }

    private Student getStudentFromResultSet(ResultSet rs) throws SQLException {
        Student student = new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("hash"),
                rs.getString("city"),
                rs.getString("phone")
        );
        return student;
    }

    public boolean verifyPassword(String password, int id) throws DbException {
        String salt = getSalt(id);
        Student user = findById(id);
        try {
            return Password.verifyPassword(password, user.getPasswordHash(), salt);
        } catch (EncryptionException e) {
            throw new DbException("Password verification problem", e);
        }
    }

    public String getSalt(int id) throws DbException {
        String salt = null;
        String request = "SELECT salt FROM students where id = ?";
        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                salt = rs.getString("salt");
            }
        } catch (SQLException e) {
            throw new DbException("Error getting tutor data from the database", e);
        }
        return salt;
    }


}
