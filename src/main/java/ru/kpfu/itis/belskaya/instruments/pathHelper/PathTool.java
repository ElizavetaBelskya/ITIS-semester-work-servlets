package ru.kpfu.itis.belskaya.instruments.pathHelper;

import ru.kpfu.itis.belskaya.exceptions.DbException;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class PathTool {

    public static String generatePath(ServletContext context, String newPath) {
        return context.getContextPath() + newPath;
    }

    public InputStream getPropertiesPath(ServletContext context) throws FileNotFoundException, DbException {
        String classpath = null;
        try {
            classpath = context.getResource("").getPath();
        } catch (MalformedURLException e) {
            throw new DbException("Error of connection", e);
        }

        String configPath = classpath + "WEB-INF/" + "config.properties";
        return new FileInputStream(configPath);
    }

}
