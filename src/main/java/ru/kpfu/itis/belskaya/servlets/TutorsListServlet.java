package ru.kpfu.itis.belskaya.servlets;

import ru.kpfu.itis.belskaya.entities.Tutor;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.services.SubjectsService;
import ru.kpfu.itis.belskaya.services.TutorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/all_tutors")
public class TutorsListServlet extends HttpServlet {

    private TutorService tutorService;
    private SubjectsService subjectsService;

    private int pageEntriesCount = 6;

    @Override
    public void init() throws ServletException {
        tutorService = (TutorService) getServletContext().getAttribute("tutorService");
        subjectsService = (SubjectsService) getServletContext().getAttribute("subjectsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            boolean sorted = req.getParameter("sorted") == null? false : req.getParameter("sorted").equals("0")? false: true;
            String subject = req.getParameter("subject") == null? "Any": req.getParameter("subject");
            int page = req.getParameter("page") == null? 1 : Integer.parseInt(req.getParameter("page"));
            req.setAttribute("subjects", subjectsService.getSubjects());
            List<Tutor> tutorList = null;
            if (!sorted && subject.equals("Any")) {
                tutorList = tutorService.getPage(pageEntriesCount, page);
            } else if (sorted && subject.equals("Any")) {
               tutorList = tutorService.getPageOrderByRating(pageEntriesCount, page);
            } else if (sorted && !subject.equals("Any")) {
                tutorList = tutorService.getPageOrderByRatingWithSubject(pageEntriesCount, page, subject);
            } else if (!sorted && !subject.equals("Any")) {
                tutorList = tutorService.getPageWithSubject(pageEntriesCount, page, subject);
            }

            int countOfTutors = tutorService.getCountOfTutors(subject);
            int countOfPages = (int) Math.ceil(Double.valueOf(countOfTutors)/pageEntriesCount);
            req.setAttribute("countOfPages", countOfPages);
            req.setAttribute("page", page);
            req.setAttribute("subjects", subjectsService.getSubjects());
            req.setAttribute("subject", subject);
            req.setAttribute("sorted", sorted);
            req.setAttribute("tutors", tutorList);
            getServletContext().getRequestDispatcher("/WEB-INF/views/tutorsListPage.jsp").forward(req, resp);
        } catch (DbException e) {
            req.setAttribute("alert", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }
    }


}
