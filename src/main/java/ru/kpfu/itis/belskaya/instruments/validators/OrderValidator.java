package ru.kpfu.itis.belskaya.instruments.validators;

import javax.servlet.http.HttpServletRequest;

public class OrderValidator {


    public static boolean validateRequest(HttpServletRequest req) {
        if (req.getParameter("subject") == null || req.getParameter("format") == null || req.getParameter("gender") == null
                || req.getParameter("rating") == null || req.getParameter("subject").isEmpty() || req.getParameter("format").isEmpty()
                || req.getParameter("gender").isEmpty() || req.getParameter("rating").isEmpty() || req.getParameter("price") == null ||
                req.getParameter("price").isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
