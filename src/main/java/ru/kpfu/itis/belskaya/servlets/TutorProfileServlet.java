package ru.kpfu.itis.belskaya.servlets;

import ru.kpfu.itis.belskaya.entities.Tutor;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.instruments.pathHelper.PathTool;
import ru.kpfu.itis.belskaya.services.TutorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/my_profile_tutor")
public class TutorProfileServlet extends HttpServlet {

    private TutorService tutorService;

    @Override
    public void init() throws ServletException {
        tutorService = (TutorService) getServletContext().getAttribute("tutorService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getAttribute("id");
        try {
            Tutor tutor = tutorService.getTutor(id);
            req.setAttribute("name", tutor.getName());
            req.setAttribute("email", tutor.getEmail());
            req.setAttribute("phone", tutor.getPhone());
            req.setAttribute("city", tutor.getCity());
            req.setAttribute("rating", tutor.getRating());
            req.setAttribute("gender", tutor.isGender()?"Male":"Female");
            req.setAttribute("subjects", tutor.getSubjects());
            getServletContext().getRequestDispatcher("/WEB-INF/views/tutorProfilePage.jsp").forward(req, resp);
        } catch (DbException e) {
            req.setAttribute("alert", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
