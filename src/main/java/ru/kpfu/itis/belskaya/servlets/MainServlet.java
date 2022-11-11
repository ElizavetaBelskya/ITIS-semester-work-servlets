package ru.kpfu.itis.belskaya.servlets;

import ru.kpfu.itis.belskaya.entities.User;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.exceptions.UserNotFoundException;
import ru.kpfu.itis.belskaya.instruments.pathHelper.PathTool;
import ru.kpfu.itis.belskaya.services.StudentService;
import ru.kpfu.itis.belskaya.services.TutorService;
import ru.kpfu.itis.belskaya.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/main")
public class MainServlet extends HttpServlet {

    private static final String WRONG_PASSWORD_ANSWER = "This password is wrong";

    private static final String UNFILLED_FORM_ANSWER = "You should fill all forms";
    private TutorService tutorService;
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        tutorService = (TutorService) getServletContext().getAttribute("tutorService");
        studentService = (StudentService) getServletContext().getAttribute("studentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/mainPage.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        UserService userService;
        switch (role) {
            case "tutor":
                userService = tutorService;
                break;
            case "student":
                userService = studentService;
                break;
            default:
                userService = studentService;
        }

        if (email == null || email.isEmpty() || password == null || password.isEmpty() && role != null) {
            req.setAttribute("alertMessage", UNFILLED_FORM_ANSWER);
            getServletContext().getRequestDispatcher("/WEB-INF/views/mainPage.jsp").forward(req, resp);
        } else {
            try {
                User user = userService.signIn(email, password);
                if (user != null) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("id", user.getId());
                    session.setAttribute("role", role);
                    if (role.equals("student")) {
                        resp.sendRedirect(PathTool.generatePath(getServletContext(), "/my_profile_student"));
                    } else if (role.equals("tutor")) {
                        resp.sendRedirect(PathTool.generatePath(getServletContext(), "/my_profile_tutor"));
                    }
                } else {
                    req.setAttribute("alertMessage", WRONG_PASSWORD_ANSWER);
                    req.setAttribute("email", email);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/mainPage.jsp").forward(req, resp);
                }
            } catch (UserNotFoundException | DbException e) {
                req.setAttribute("alertMessage", e.getMessage());
                getServletContext().getRequestDispatcher("/WEB-INF/views/mainPage.jsp").forward(req, resp);
            }
        }


    }


}
