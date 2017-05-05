package com.isacademy.jjdd1.czterystrony.servlets;

import com.isacademy.jjdd1.czterystrony.dbviews.Views;
import com.isacademy.jjdd1.czterystrony.model.InvestFundDetails;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/4analysis")
public class HomePageServlet extends HttpServlet {

    @Inject
    Views views;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        if (!req.getRequestURI().equals("/4analysis")) {
            resp.sendRedirect("http://localhost:8080/4analysis");
            return;
        }

        List<InvestFundDetails> allInvestFunds = views.getAllFunds();
        List<InvestFundDetails> promotedInvestFunds = views.getPromotedFunds();
        List<InvestFundDetails> notPromotedInvestFunds = views.getNotPromotedFunds();

        boolean dataFound = false;
        if (!allInvestFunds.isEmpty()) {
            dataFound = true;
        }

        req.setAttribute("promotedInvestFunds", promotedInvestFunds);
        req.setAttribute("notPromotedInvestFunds", notPromotedInvestFunds);
        req.setAttribute("allInvestFunds", allInvestFunds);
        req.setAttribute("dataFound", dataFound);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }

}
