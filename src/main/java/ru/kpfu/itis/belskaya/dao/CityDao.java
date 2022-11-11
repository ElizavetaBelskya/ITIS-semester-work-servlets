package ru.kpfu.itis.belskaya.dao;

import ru.kpfu.itis.belskaya.exceptions.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CityDao implements InformationEntityDao {
    private Connection conn;

    public CityDao(Connection conn) {
        this.conn = conn;
    }

    public List<String> getAll() throws DbException {
        ArrayList<String> list = new ArrayList<>();
        String request = "SELECT * FROM cities";
        try (Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                list.add(rs.getString("title"));
            }
        } catch (SQLException e) {
            throw new DbException("Error getting cities from the database", e);
        }
        return list;
    }


}
