package ru.kpfu.itis.belskaya.dao;

import ru.kpfu.itis.belskaya.instruments.pathHelper.PathTool;
import ru.kpfu.itis.belskaya.exceptions.DbException;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionService {
    private static String dbUrl;
    private static String dbPassword;
    private static String dbUser;
    private static DatabaseConnectionService connectionInstance;
    private static Connection connection;

    private DatabaseConnectionService() {
    }

    private void setConnection(ServletContext context) throws SQLException, DbException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DbException("Driver class not found");
        }

        Properties prop = new Properties();
        try (InputStream input = (new PathTool()).getPropertiesPath(context)) {
            prop.load(input);
            dbUrl = prop.getProperty("db.url");
            dbPassword = prop.getProperty("db.password");
            dbUser = prop.getProperty("db.user");
        } catch (IOException e) {
            throw new SQLException("Properties error", e);
        }

        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public static DatabaseConnectionService getInstance(ServletContext context) throws DbException {
        if (connectionInstance == null) {
            connectionInstance = new DatabaseConnectionService();
        }

        try {
            if (connection == null || connection.isClosed()) {
                connectionInstance.setConnection(context);
            }
        } catch (SQLException e) {
            throw new DbException("Database connection error", e);
        }

        return connectionInstance;
    }

    public Connection getConnection() {
        return connection;
    }

}
