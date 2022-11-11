package ru.kpfu.itis.belskaya.servlets;

import ru.kpfu.itis.belskaya.instruments.validators.RegistrationValidator;
import ru.kpfu.itis.belskaya.instruments.pathHelper.PathTool;
import ru.kpfu.itis.belskaya.services.CityService;
import ru.kpfu.itis.belskaya.services.SubjectsService;
import ru.kpfu.itis.belskaya.services.TutorService;
import ru.kpfu.itis.belskaya.entities.Tutor;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.exceptions.DuplicateEntryException;
import ru.kpfu.itis.belskaya.exceptions.MatchingEmailException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/registration_tutor")
public class TutorRegistrationServlet extends HttpServlet {

    private static final String SUCCESS_MESSAGE = "You have been successfully registered.";
    private TutorService tutorService;
    private CityService cityService;
    private SubjectsService subjectsService;

    @Override
    public void init() throws ServletException {
        tutorService = (TutorService) getServletContext().getAttribute("tutorService");
        cityService = (CityService) getServletContext().getAttribute("cityService");
        subjectsService = (SubjectsService) getServletContext().getAttribute("subjectsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            req.setAttribute("cities", cityService.getCities());
            req.setAttribute("subjects", subjectsService.getSubjects());
            getServletContext().getRequestDispatcher("/WEB-INF/views/tutorRegistrationPage.jsp").forward(req, resp);
        } catch (DbException e) {
            req.setAttribute("alert", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String city = req.getParameter("city");
            String phone = req.getParameter("phone");
            String password = req.getParameter("password");
            boolean isWorkingOnline = req.getParameter("isWorkingOnline") == null? false : true;
            boolean gender = req.getParameter("gender").equals("1")? true : false;
            List<String> allSubjects = subjectsService.getSubjects();
            List<String> tutorSubjects = new ArrayList<>();

            for (String subject : allSubjects) {
                if (req.getParameter(subject) != null) {
                    tutorSubjects.add(subject);
                }
            }

            String answer = generateResult(name, email, password, city, isWorkingOnline, gender, tutorSubjects, phone);
            if (answer.equals(SUCCESS_MESSAGE)) {
                resp.sendRedirect(PathTool.generatePath(getServletContext(), "/main"));
            } else {
                req.setAttribute("subjects", subjectsService.getSubjects());
                req.setAttribute("cities", cityService.getCities());
                req.setAttribute("name", name);
                req.setAttribute("email", email);
                req.setAttribute("phone", phone);
                req.setAttribute("city", city);
                req.setAttribute("gender", gender);
                req.setAttribute("isWorkingOnline", isWorkingOnline? true : null);
                req.setAttribute("answer", answer);
                req.setAttribute("answerTitle", "Sorry!");
                getServletContext().getRequestDispatcher("/WEB-INF/views/tutorRegistrationPage.jsp").forward(req, resp);
            }
        } catch (DbException e) {
            req.setAttribute("alert", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }
    }

    private String generateResult(String name, String email, String password, String city, boolean isWorkingOnline, boolean gender, List<String> subjects, String phone) {
        String answer = RegistrationValidator.generateTutorAnswer(name, email, password, city, subjects, phone);
        if (answer.equals(RegistrationValidator.SUCCESS_VALIDATION_ANSWER)) {
            try {
                Tutor tutor = new Tutor(name, email, password, city, isWorkingOnline, gender, subjects, phone);
                tutorService.add(tutor);
                return SUCCESS_MESSAGE;
            } catch (DbException | DuplicateEntryException | MatchingEmailException e) {
                return e.getMessage();
            }
        } else {
            return answer;
        }
    }

}
