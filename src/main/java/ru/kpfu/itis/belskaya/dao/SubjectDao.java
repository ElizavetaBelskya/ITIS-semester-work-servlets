package ru.kpfu.itis.belskaya.dao;

import ru.kpfu.itis.belskaya.exceptions.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao implements InformationEntityDao {
    private Connection conn;

    public SubjectDao(Connection conn) {
        this.conn = conn;
    }

    public List<String> getAll() throws DbException {
        String request = "SELECT * FROM subjects";
        ArrayList<String> subjectList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                subjectList.add(rs.getString("subject"));
            }
        } catch (SQLException e) {
            throw new DbException("Error getting subjects from the database", e);
        }
        return subjectList;
    }
}
