package com.isacademy.jjdd1.czterystrony.servlets;

import com.isacademy.jjdd1.czterystrony.clients.InvestFundPopularityReportClient;
import isacademy.jjdd1.czterystrony.reports.persistence.model.InvestFundPopularity;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/4analysis/raporty/popularnosc")
public class PopularityReportServlet extends HttpServlet {

    @Inject
    InvestFundPopularityReportClient client;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        List<InvestFundPopularity> popularities = client.getAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/popularity-report.jsp");
        req.setAttribute("popularities", popularities);
        dispatcher.forward(req, resp);
    }
}
