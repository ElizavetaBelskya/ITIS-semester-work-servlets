package ru.kpfu.itis.belskaya.dao;

import ru.kpfu.itis.belskaya.exceptions.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingDao {

    private Connection conn;

    public RatingDao(Connection conn) {
        this.conn = conn;
    }


    public boolean hasRate(int tutorId, int studentId) throws DbException {
        String request = "select exists (select rate from rates where tutor_id = ? and student_id = ?)";
        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setInt(1, tutorId);
            statement.setInt(2, studentId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DbException("Problems with rates database", e);
        }
    }



    public float insertRate(int tutorId, int studentId, int rate) throws DbException {
        String request;
        if (hasRate(tutorId, studentId)) {
            request = "update rates set rate = ? where tutor_id = ? and student_id = ?";
            try (PreparedStatement statement = conn.prepareStatement(request)){
                statement.setInt(1, rate);
                statement.setInt(2, tutorId);
                statement.setInt(3, studentId);
                statement.execute();
            } catch (SQLException e) {
                throw new DbException("Problems with rates database", e);
            }
        } else {
            request = "insert into rates(tutor_id, student_id, rate) values (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(request)){
                statement.setInt(1, tutorId);
                statement.setInt(2, studentId);
                statement.setInt(3, rate);
                statement.execute();
            } catch (SQLException e) {
                throw new DbException("Problems with rates database", e);
            }
        }
        return recalculateRating(tutorId);
    }


    private float recalculateRating(int tutorId) throws DbException {
        String request = "select rate from rates where tutor_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(request)){
            statement.setInt(1, tutorId);
            ResultSet rs = statement.executeQuery();
            List<Integer> rates = new ArrayList<Integer>();
            while (rs.next()) {
                rates.add(rs.getInt(1));
            }

            Float size = Float.valueOf(rates.size());
            Float newRating = Float.valueOf((rates.stream().mapToInt(x -> x).sum()))/size;
            float newRatingRounded = Math.round(newRating * 100.0f) / 100.0f;
            return newRatingRounded;
        } catch (SQLException e) {
            throw new DbException();
        }
    }




}
