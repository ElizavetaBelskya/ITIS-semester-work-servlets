package ru.kpfu.itis.belskaya.servlets;

import ru.kpfu.itis.belskaya.entities.Order;
import ru.kpfu.itis.belskaya.entities.Student;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.instruments.pathHelper.PathTool;
import ru.kpfu.itis.belskaya.services.OrderService;
import ru.kpfu.itis.belskaya.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/my_students")
public class StudentsOfTutorServlet extends HttpServlet {

    private StudentService studentService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        studentService = (StudentService) getServletContext().getAttribute("studentService");
        orderService = (OrderService) getServletContext().getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = (int) req.getAttribute("id");
            List<Order> orders = orderService.getTutorOrders(id);
            List<Student> students = new ArrayList<>();
            for (Order order : orders) {
                Student student = studentService.getStudent(order.getAuthorId());
                students.add(student);
            }
            req.setAttribute("orders", orders);
            req.setAttribute("students", students);
            req.setAttribute("actionTitle", "It was created");
            getServletContext().getRequestDispatcher("/WEB-INF/views/studentOfTutorPage.jsp").forward(req, resp);
        } catch (DbException e) {
            req.setAttribute("alert", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getParameter("action");
            if (action.equals("reject")) {
                int orderId = Integer.parseInt(req.getParameter("reject"));
                try {
                    orderService.rejectOrder(orderId);
                    resp.sendRedirect(req.getRequestURI() + "?rejected=true");
                } catch (DbException e) {
                    req.setAttribute("alert", e.getMessage());
                    getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
                }
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/views/studentOfTutorPage.jsp").forward(req, resp);
            }

    }


}
