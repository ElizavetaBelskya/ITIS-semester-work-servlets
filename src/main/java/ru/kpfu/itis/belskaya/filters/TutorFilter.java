package ru.kpfu.itis.belskaya.filters;


import ru.kpfu.itis.belskaya.instruments.pathHelper.PathTool;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/my_students", "/new_orders", "/tutor_profile", "/my_profile_tutor"})
public class TutorFilter extends HttpFilter {

    private Object id;
    private Object role;

    @Override
    public void init() throws ServletException {
        id = null;
        role = null;
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        id = session.getAttribute("id");
        role = session.getAttribute("role");
        if (id != null && role.equals("tutor")) {
            req.setAttribute("id", id);
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(PathTool.generatePath(req.getServletContext(), "/main"));
        }
    }

}
