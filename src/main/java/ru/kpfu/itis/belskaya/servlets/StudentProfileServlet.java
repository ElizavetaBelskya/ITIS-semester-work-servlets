package ru.kpfu.itis.belskaya.servlets;

import ru.kpfu.itis.belskaya.entities.Student;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.instruments.pathHelper.PathTool;
import ru.kpfu.itis.belskaya.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/my_profile_student")
public class StudentProfileServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        studentService = (StudentService) getServletContext().getAttribute("studentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = (int) req.getAttribute("id");
            Student student = studentService.getStudent(id);
            req.setAttribute("name", student.getName());
            req.setAttribute("email", student.getEmail());
            req.setAttribute("phone", student.getPhone());
            req.setAttribute("city", student.getCity());
            getServletContext().getRequestDispatcher("/WEB-INF/views/studentProfilePage.jsp").forward(req, resp);
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
