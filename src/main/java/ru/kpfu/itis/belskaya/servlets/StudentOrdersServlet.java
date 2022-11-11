package ru.kpfu.itis.belskaya.servlets;

import ru.kpfu.itis.belskaya.entities.Order;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/my_orders")
public class StudentOrdersServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = (OrderService) getServletContext().getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getAttribute("id");
        List<Order> studentOrders;
        try {
            studentOrders = orderService.getStudentOrders(id);
        } catch (DbException e) {
            studentOrders = new ArrayList<>();
        }
        req.setAttribute("orders", studentOrders);
        req.setAttribute("actionTitle", "It was created ");
        getServletContext().getRequestDispatcher("/WEB-INF/views/studentOrdersPage.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = (int) req.getAttribute("id");
            if (req.getParameter("delete") != null) {
                int orderId = Integer.parseInt(req.getParameter("delete"));
                orderService.deleteOrder(orderId);
                resp.sendRedirect(req.getRequestURI() + "?deleted=true");
            } else {
                req.setAttribute("orders", orderService.getStudentOrders(id));
                req.setAttribute("actionTitle", "It was created ");
                getServletContext().getRequestDispatcher("/WEB-INF/views/studentOrdersPage.jsp").forward(req, resp);
            }
        } catch (DbException e) {
            req.setAttribute("alert", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }

    }
}
