package ru.kpfu.itis.belskaya.servlets;

import ru.kpfu.itis.belskaya.entities.Order;
import ru.kpfu.itis.belskaya.entities.Tutor;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.instruments.pathHelper.PathTool;
import ru.kpfu.itis.belskaya.services.OrderService;
import ru.kpfu.itis.belskaya.services.RatingService;
import ru.kpfu.itis.belskaya.services.TutorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/my_tutors")
public class TutorAndCandidatesListServlet extends HttpServlet {

    private OrderService orderService;
    private TutorService tutorService;

    private RatingService ratingService;

    @Override
    public void init() throws ServletException {
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        tutorService = (TutorService) getServletContext().getAttribute("tutorService");
        ratingService = (RatingService) getServletContext().getAttribute("ratingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = (int) req.getAttribute("id");
            List<Tutor> approvedTutors = tutorService.getApprovedTutors(id);
            List<Order> orderList = orderService.getUncompletedStudentOrders(id);
            List<List<Tutor>> candidatesLists = new ArrayList<>();
            for (Order order : orderList) {
                List<Tutor> candidates = tutorService.getCandidatesByOrderId(order.getId());
                candidatesLists.add(candidates);
            }
            req.setAttribute("approvedTutors", approvedTutors);
            req.setAttribute("uncompletedOrders", orderList);
            req.setAttribute("candidatesLists", candidatesLists);
            boolean noCandidates = (approvedTutors.size() == 0) && (orderList.size() > 0) &&
                    (candidatesLists.stream().mapToInt(x -> x.size()).sum() == 0);
            req.setAttribute("noCandidates", noCandidates);
            getServletContext().getRequestDispatcher("/WEB-INF/views/tutorsAndCandidatesListPage.jsp").forward(req, resp);
        } catch (DbException e) {
            req.setAttribute("alert", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = (int) req.getAttribute("id");
            String action = req.getParameter("action");
            if (action.equals("reject") && (req.getParameter("reject") != null)) {
                int tutorId = Integer.parseInt(req.getParameter("reject"));
                orderService.rejectTutor(tutorId, id);
                resp.sendRedirect(PathTool.generatePath(getServletContext(), "/my_tutors?status=rejected"));
            } else if (action.equals("choose") && req.getParameter("chooseTutorId")!= null && req.getParameter("chooseOrderId") != null) {
                int tutorId = Integer.parseInt(req.getParameter("chooseTutorId"));
                int orderId = Integer.parseInt(req.getParameter("chooseOrderId"));
                orderService.approveTutor(orderId, tutorId);
                resp.sendRedirect(PathTool.generatePath(getServletContext(), "/my_tutors?status=chosen"));
            } else if (action.equals("rated") && req.getParameter("starCount") != null && req.getParameter("idRatedTutor") != null) {
                int rate = Integer.parseInt(req.getParameter("starCount"));
                int tutorId = Integer.parseInt(req.getParameter("idRatedTutor"));
                float newRating = ratingService.insertRate(tutorId, id, rate);
                tutorService.setTutorRating(tutorId, newRating);
                resp.sendRedirect(PathTool.generatePath(getServletContext(), "/my_tutors?status=rated"));
            } else {
                List<Tutor> approvedTutors = tutorService.getApprovedTutors(id);
                List<Order> orderList = orderService.getUncompletedStudentOrders(id);
                List<List<Tutor>> candidatesLists = new ArrayList<>();
                for (Order order : orderList) {
                    List<Tutor> candidates = tutorService.getCandidatesByOrderId(order.getId());
                    candidatesLists.add(candidates);
                }
                req.setAttribute("approvedTutors", approvedTutors);
                req.setAttribute("uncompletedOrders", orderList);
                req.setAttribute("candidatesLists", candidatesLists);

                boolean noCandidates = (approvedTutors.size() == 0) && (orderList.size() > 0) &&
                        (candidatesLists.stream().mapToInt(x -> x.size()).sum() == 0);
                req.setAttribute("noCandidates", noCandidates);
                getServletContext().getRequestDispatcher("/WEB-INF/views/tutorsAndCandidatesListPage.jsp").forward(req, resp);
            }

        } catch (DbException e) {
            req.setAttribute("alert", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }

    }
}
