package ru.kpfu.itis.belskaya.listeners;

import ru.kpfu.itis.belskaya.dao.*;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.io.File;
import java.sql.Connection;

@WebListener
public class MainListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Connection conn = null;
        try {
            conn = DatabaseConnectionService.getInstance(context).getConnection();
        } catch (DbException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        context.setAttribute("studentService", new StudentService(new StudentDao(conn)));
        context.setAttribute("tutorService", new TutorService(new TutorDao(conn)));
        context.setAttribute("orderService", new OrderService(new OrderDao(conn)));
        context.setAttribute("cityService", new CityService(new CityDao(conn)));
        context.setAttribute("subjectsService", new SubjectsService(new SubjectDao(conn)));
        context.setAttribute("ratingService", new RatingService(new RatingDao(conn)));
    }
    
}
