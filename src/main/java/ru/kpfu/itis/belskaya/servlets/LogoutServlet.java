package ru.kpfu.itis.belskaya.servlets;

import ru.kpfu.itis.belskaya.instruments.pathHelper.PathTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("id");
        session.removeAttribute("role");
        resp.sendRedirect(PathTool.generatePath(getServletContext(), "/main"));
    }
}
