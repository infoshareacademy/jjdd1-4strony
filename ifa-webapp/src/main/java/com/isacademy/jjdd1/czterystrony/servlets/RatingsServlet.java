package com.isacademy.jjdd1.czterystrony.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/4analysis/test")
public class RatingsServlet extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(RatingsServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate start = LocalDate.parse(req.getParameter("start"));
        LocalDate end = LocalDate.parse(req.getParameter("end"));

        log.info(start.toString(), end.toString());
        resp.setContentType("text/plain");
        resp.getWriter().write(start.plusDays(10).toString());
    }
}
