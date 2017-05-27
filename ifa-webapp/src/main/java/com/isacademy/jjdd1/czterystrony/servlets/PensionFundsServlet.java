package com.isacademy.jjdd1.czterystrony.servlets;

import isacademy.jjdd1.czterystrony.webapp.persistence.dbviews.Views;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.PensionFundDetails;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.PensionFundDetailsRepository;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/4analysis/pensionfunds")
public class PensionFundsServlet extends HttpServlet {

    @Inject
    Views views;

    @Inject
    PensionFundDetailsRepository pensionFundDetailsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        List<PensionFundDetails> allPensionFunds = views.getAllPensionFunds();

        boolean dataFound = false;
        if (!allPensionFunds.isEmpty()) {
            dataFound = true;
        }

        req.setAttribute("dataFound", dataFound);
        req.setAttribute("allPensionFunds", allPensionFunds);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/pensionfund.jsp");
        dispatcher.forward(req, resp);
    }
}
