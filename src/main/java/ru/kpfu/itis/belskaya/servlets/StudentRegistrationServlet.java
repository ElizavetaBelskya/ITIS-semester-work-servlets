package ru.kpfu.itis.belskaya.servlets;

import ru.kpfu.itis.belskaya.instruments.validators.RegistrationValidator;
import ru.kpfu.itis.belskaya.instruments.pathHelper.PathTool;
import ru.kpfu.itis.belskaya.services.CityService;
import ru.kpfu.itis.belskaya.services.StudentService;
import ru.kpfu.itis.belskaya.entities.Student;
import ru.kpfu.itis.belskaya.exceptions.MatchingEmailException;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.exceptions.DuplicateEntryException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration_student")
public class StudentRegistrationServlet extends HttpServlet {

    private static final String SUCCESS_MESSAGE = "You have been successfully registered.";
    private StudentService studentService;
    private CityService cityService;

    @Override
    public void init() throws ServletException {
        studentService = (StudentService) getServletContext().getAttribute("studentService");
        cityService = (CityService) getServletContext().getAttribute("cityService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            req.setAttribute("cities", cityService.getCities());
            getServletContext().getRequestDispatcher("/WEB-INF/views/studentRegistrationPage.jsp").forward(req, resp);
        } catch (DbException e) {
            req.setAttribute("alert", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String city = req.getParameter("city");
            String password = req.getParameter("password");
            String phone = req.getParameter("phone");
            String answer = generateResult(name, email, password, city, phone);
            if (answer.equals(SUCCESS_MESSAGE)) {
                resp.sendRedirect(PathTool.generatePath(getServletContext(), "/main"));
            } else {
                try {
                    req.setAttribute("cities", cityService.getCities());
                    req.setAttribute("name", name);
                    req.setAttribute("email", email);
                    req.setAttribute("phone", phone);
                    req.setAttribute("city", city);
                    req.setAttribute("answerTitle", "Sorry!");
                    req.setAttribute("answer", answer);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/studentRegistrationPage.jsp").forward(req, resp);
                } catch (DbException e) {
                    req.setAttribute("alert", e.getMessage());
                    getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
                }
            }

    }

    private String generateResult(String name, String email, String password, String city, String phone) {
        String answer = RegistrationValidator.generateStudentAnswer(name, email, password, city, phone);
        if (answer.equals(RegistrationValidator.SUCCESS_VALIDATION_ANSWER)) {
            Student student = new Student(name, email, password, city, phone);
            try {
                studentService.add(student);
                return SUCCESS_MESSAGE;
            } catch (DbException | DuplicateEntryException | MatchingEmailException e) {
                return e.getMessage();
            }
        } else {
            return answer;
        }
    }

}
