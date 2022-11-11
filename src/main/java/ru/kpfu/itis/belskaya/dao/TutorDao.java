package ru.kpfu.itis.belskaya.dao;

import ru.kpfu.itis.belskaya.entities.Tutor;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.exceptions.EncryptionException;
import ru.kpfu.itis.belskaya.instruments.passwordHelper.Password;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TutorDao implements UserDao<Tutor> {

    private Connection conn;

    public TutorDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Tutor user) throws DbException {
        String salt = "";
        int newId = getLastId(conn, "tutor") + 1;
        String request = "INSERT INTO tutors(id, name,hash,gender,email,rating,city,is_working_online, phone, salt) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(request)) {
            preparedStatement.setInt(1, newId);
            preparedStatement.setString(2, user.getName());
            salt = Password.generateSalt(user.getPasswordHash().length());
            preparedStatement.setString(3, Password.hashPassword(user.getPasswordHash(), salt));
            preparedStatement.setBoolean(4, user.isGender());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setFloat(6, user.getRating());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setBoolean(8, user.isWorkingOnline());
            preparedStatement.setString(9, user.getPhone());
            preparedStatement.setString(10, salt);
            preparedStatement.executeUpdate();

            addTutorSubjects(user.getSubjects(), newId);

        } catch (SQLException | EncryptionException e) {
            throw new DbException("Error writing tutor information to the database", e);
        }

    }


    private void addTutorSubjects(List<String> subjects, int tutorId) throws DbException {
        String subjectsRequest = "Insert into tutor_subjects (tutor_id,subject) values (?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(subjectsRequest)) {
            for (String subject : subjects) {
                statement.setInt(1, tutorId);
                statement.setString(2, subject);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new DbException("Error writing tutor subjects to the database", e);
        }
    }


    @Override
    public Tutor findById(int id) throws DbException {
        String request = "SELECT * FROM tutors where id = (?)";
        Tutor user = null;
        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = getTutorFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DbException("Error getting tutor data from the database", e);
        }
        return user;
    }

    private List<String> getTutorSubjects(int id) throws DbException {
        String subjectsRequest = "SELECT * FROM tutor_subjects WHERE tutor_id = ?";
        List<String> subjects = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(subjectsRequest)) {
            ps.setInt(1, id);
            ResultSet subjectsResult = ps.executeQuery();
            while (subjectsResult.next()) {
                subjects.add(subjectsResult.getString("subject"));
            }
        } catch (SQLException e) {
            throw new DbException("Error getting tutor subjects from the database", e);
        }
        return subjects;
    }

    @Override
    public List<Tutor> getAll() throws DbException {
        ArrayList<Tutor> userList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM tutors");
            while (rs.next()) {
                Tutor user = getTutorFromResultSet(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DbException("Error getting data from the database", e);
        }
        return userList;
    }

    public List<Tutor> getPage(int count, int page) throws DbException {
        ArrayList<Tutor> userList = new ArrayList<>();
        String request = "SELECT * FROM tutors limit ? offset ?";
        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setInt(1, count);
            statement.setInt(2, count*(page-1));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Tutor user = getTutorFromResultSet(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DbException("Error getting data from the database", e);
        }
        return userList;
    }

    public List<Tutor> getPageOrderByRating(int count, int page) throws DbException {
        ArrayList<Tutor> userList = new ArrayList<>();
        String request = "SELECT * FROM tutors order by rating desc limit ? offset ?";
        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setInt(1, count);
            statement.setInt(2, count*(page-1));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Tutor user = getTutorFromResultSet(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DbException("Error getting data from the database", e);
        }
        return userList;
    }

    public List<Tutor> getPageOrderByRatingWithSubject(int count, int page, String subject) throws DbException {
        ArrayList<Tutor> userList = new ArrayList<>();
        String request = "SELECT * FROM tutors inner join tutor_subjects ts on tutors.id = ts.tutor_id where subject = ? order by rating desc limit ? offset ?";
        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setString(1, subject);
            statement.setInt(2, count);
            statement.setInt(3, count*(page-1));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Tutor user = getTutorFromResultSet(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DbException("Error getting data from the database", e);
        }
        return userList;
    }

    public List<Tutor> getPageWithSubject(int count, int page, String subject) throws DbException {
        ArrayList<Tutor> userList = new ArrayList<>();
        String request = "SELECT * FROM tutors inner join tutor_subjects ts on tutors.id = ts.tutor_id where subject = ? limit ? offset ?";
        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setString(1, subject);
            statement.setInt(2, count);
            statement.setInt(3, count*(page-1));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Tutor user = getTutorFromResultSet(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DbException("Error getting data from the database", e);
        }
        return userList;
    }

    public int getCountOfTutorsBySubject(String subject) throws DbException {
        String request;
        int count = 0;
        if (subject.equals("Any")) {
            request = "SELECT count(*) as cnt FROM tutors";
            try (Statement statement = conn.createStatement()) {
                ResultSet rs = statement.executeQuery(request);
                if (rs.next()) {
                    count = rs.getInt("cnt");
                }
                return count;
            } catch (SQLException e) {
                throw new DbException("Error getting tutor data from the database", e);
            }
        } else {
            request = "SELECT count(*) as cnt FROM (tutors inner join tutor_subjects ts on tutors.id = ts.tutor_id) where subject = ?";
            try (PreparedStatement statement = conn.prepareStatement(request)) {
                statement.setString(1, subject);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("cnt");
                }
                return count;
            } catch (SQLException e) {
                throw new DbException("Error getting tutor data from the database", e);
            }
        }
    }

    public int findUserByEmail(String email) throws DbException {
        return findUserByEmail(conn, email, "tutor");
    }

    public int findUserByPhone(String phone) throws DbException {
        return findUserByPhone(conn, phone, "tutor");
    }

    public List<Tutor> getCandidatesByOrderId(int orderId) throws DbException {
        List<Tutor> candidates = new ArrayList<>();
        String request = "select * from tutors inner join orders_to_tutors on tutors.id = orders_to_tutors.tutor_id where order_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Tutor tutor = getTutorFromResultSet(rs);
                candidates.add(tutor);
            }

        } catch (SQLException e) {
            throw new DbException("Error getting candidates data from the database", e);
        }
        return candidates;
    }

    public List<Tutor> getApprovedTutorsOfThisStudent(int studentId) throws DbException {
        String request = "select distinct tutor_id from orders where author_id = ? and tutor_id is not null";
        List<Tutor> approvedTutors = new ArrayList<Tutor>();
        try (PreparedStatement statement = conn.prepareStatement(request)){
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            ArrayList<Integer> approvedTutorsId = new ArrayList<>();
            while (rs.next()) {
                approvedTutorsId.add(rs.getInt(1));
            }

            for (Integer tutorId : approvedTutorsId) {
                Tutor tutor = findById(tutorId);
                approvedTutors.add(tutor);
            }

        } catch (SQLException e) {
            throw new DbException("Error getting tutors from the database", e);
        }
        return approvedTutors;
    }


    private Tutor getTutorFromResultSet(ResultSet rs) throws SQLException, DbException {
        int id = rs.getInt("id");
        List<String> subjects = getTutorSubjects(id);
        Tutor tutor = new Tutor(
                id,
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("hash"),
                rs.getString("city"),
                rs.getString("phone"),
                rs.getFloat("rating"),
                rs.getBoolean("gender"),
                rs.getBoolean("is_working_online"),
                subjects
        );
        return tutor;
    }


    public void setTutorRating(int tutorId, float newRating) throws DbException {
        String request = "update tutors set rating = ? where id = ?";
        try (PreparedStatement statement = conn.prepareStatement(request)){
            statement.setFloat(1, newRating);
            statement.setInt(2, tutorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error getting tutor's rating from the database", e);
        }
    }

    public boolean verifyPassword(String password, int id) throws DbException {
        String salt = getSalt(id);
        Tutor user = findById(id);
        try {
            return Password.verifyPassword(password, user.getPasswordHash(), salt);
        } catch (EncryptionException e) {
            throw new DbException("Password verification problem", e);
        }
    }

    public String getSalt(int id) throws DbException {
        String salt = null;
        String request = "SELECT salt FROM tutors where id = ?";
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
