package ru.kpfu.itis.belskaya.servlets;

import ru.kpfu.itis.belskaya.entities.Order;
import ru.kpfu.itis.belskaya.entities.Tutor;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.services.OrderService;
import ru.kpfu.itis.belskaya.services.TutorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/new_orders")
public class NewOrdersServlet extends HttpServlet {

    private OrderService orderService;
    private TutorService tutorService;

    @Override
    public void init() throws ServletException {
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        tutorService = (TutorService) getServletContext().getAttribute("tutorService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("title", "New orders");
            int id = (int) req.getAttribute("id");
            Tutor tutor = tutorService.getTutor(id);
            List<Order> orderList = orderService.getSuitableOrders(tutor);
            List<Boolean> takenOrders = new ArrayList<Boolean>();
            for (Order order : orderList) {
                boolean isCandidate = orderService.isCandidate(id, order.getId());
                takenOrders.add(isCandidate);
            }
            req.setAttribute("orders", orderList);
            req.setAttribute("takenOrders", takenOrders);
            req.setAttribute("actionName", "It was created ");
            getServletContext().getRequestDispatcher("/WEB-INF/views/newOrdersPage.jsp").forward(req, resp);
        } catch (DbException e) {
            req.setAttribute("alert", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int tutorId = (int) req.getAttribute("id");
            String action = req.getParameter("action");
            if (action.equals("take")) {
                int orderId = Integer.parseInt(req.getParameter("take"));
                orderService.takeOrder(orderId, tutorId);
                resp.sendRedirect(req.getRequestURI() + "?status=taken");
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/views/newOrdersPage.jsp").forward(req, resp);
            }
        } catch (DbException e) {
            req.setAttribute("alert", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }
    }




}
