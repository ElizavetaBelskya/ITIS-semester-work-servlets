package ru.kpfu.itis.belskaya.dao;

import ru.kpfu.itis.belskaya.entities.User;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.exceptions.EncryptionException;
import ru.kpfu.itis.belskaya.instruments.passwordHelper.Password;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDao<T extends User> {

    void add(T user) throws DbException;

    T findById(int id) throws DbException;

    List<T> getAll() throws DbException;

    String getSalt(int id) throws DbException;

    boolean verifyPassword(String password, int id) throws DbException;
    default int findUserByEmail(Connection conn, String email, String role) throws DbException {
        int id = -1;
        String request = "";
        if (role.equals("tutor")) {
            request = "SELECT id FROM tutors where email = ?";
        } else {
            request = "SELECT id FROM students where email = ?";
        }

        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new DbException("Error getting user data from the database", e);
        }
        return id;
    }

    default int findUserByPhone(Connection conn, String phone, String role) throws DbException {
        int id = -1;
        String request = "";
        if (role.equals("tutor")) {
            request = "SELECT id FROM tutors where phone = ?";
        } else {
            request = "SELECT id FROM students where phone = ?";
        }

        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setString(1, phone);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new DbException("Error getting user data from the database", e);
        }
        return id;
    }

    default int getLastId(Connection conn, String role) throws DbException {
        String request = "";
        if (role.equals("tutor")) {
            request = "SELECT max(id) FROM tutors";
        } else {
            request = "SELECT max(id) FROM students";
        }

        try (PreparedStatement statement = conn.prepareStatement(request)) {
            ResultSet rs = statement.executeQuery();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            throw new DbException("Error getting user data from the database", e);
        }
    }


}
