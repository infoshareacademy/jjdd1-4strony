package com.isacademy.jjdd1.czterystrony.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/4analysis/statistics")
public class StatisticsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/statistics.jsp");
//        req.setAttribute("statisticsDetails", statisticsDetails);
        dispatcher.forward(req, resp);
    }
}
