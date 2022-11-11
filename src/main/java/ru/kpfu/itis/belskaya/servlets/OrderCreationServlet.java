package ru.kpfu.itis.belskaya.servlets;

import ru.kpfu.itis.belskaya.entities.Order;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.instruments.validators.OrderValidator;
import ru.kpfu.itis.belskaya.services.OrderService;
import ru.kpfu.itis.belskaya.services.SubjectsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create_order")
public class OrderCreationServlet extends HttpServlet {

    private static final String SUCCESS_MESSAGE = "You have been successfully created an order.";

    private SubjectsService subjectsService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        subjectsService = (SubjectsService) getServletContext().getAttribute("subjectsService");
        orderService = (OrderService) getServletContext().getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int authorId = (int) req.getAttribute("id");
            req.setAttribute("subjects", subjectsService.getSubjects());
            req.setAttribute("orders", orderService.getStudentOrders(authorId));
            if (req.getParameter("status") != null) {
                if (req.getParameter("status").equals("1")) {
                    String successTitle = "Congratulations!";
                    req.setAttribute("answer", SUCCESS_MESSAGE);
                    req.setAttribute("answerTitle", successTitle);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/orderCreationPage.jsp").forward(req, resp);
                }
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/views/orderCreationPage.jsp").forward(req, resp);
            }
        } catch (DbException e) {
            req.setAttribute("alert", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            int authorId = (int) req.getAttribute("id");
            req.setAttribute("subjects", subjectsService.getSubjects());
            req.setAttribute("orders", orderService.getStudentOrders(authorId));

            if (!OrderValidator.validateRequest(req)) {
                String answer ="You have to fill all forms";
                req.setAttribute("answerTitle", "Sorry!");
                req.setAttribute("answer", answer);
                getServletContext().getRequestDispatcher("/WEB-INF/views/orderCreationPage.jsp").forward(req, resp);
            } else {
                String subject = req.getParameter("subject");
                Boolean format = req.getParameter("format").equals("both")? null:
                        (req.getParameter("format").equals("online"));
                Boolean gender = req.getParameter("gender").equals("both")? null:
                        (req.getParameter("gender").equals("male"));
                float minRating = Float.parseFloat(req.getParameter("rating"));
                String description = req.getParameter("description");
                int price = Integer.parseInt(req.getParameter("price"));
                if (price > 100 && price < 10000) {
                    Order order = new Order(subject, authorId, description, gender, minRating, format, price);
                    orderService.addOrder(order);
                    resp.sendRedirect(req.getRequestURI() + "?status=1");
                } else {
                    String answer = "Put an adequate price, please";
                    req.setAttribute("answerTitle", "Sorry!");
                    req.setAttribute("answer", answer);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/orderCreationPage.jsp").forward(req, resp);
                }

            }
        } catch (DbException e) {
            req.setAttribute("alert", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }

    }


}
