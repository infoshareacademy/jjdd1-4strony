package com.isacademy.jjdd1.czterystrony.servlets;

import com.isacademy.jjdd1.czterystrony.dbviews.Views;
import com.isacademy.jjdd1.czterystrony.model.InvestFundDetails;
import com.isacademy.jjdd1.czterystrony.model.PensionFundDetails;
import com.isacademy.jjdd1.czterystrony.repositories.PensionFundDetailsRepository;

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
public class PensionFundServlet extends HttpServlet {

    @Inject
    Views views;

    @Inject
    PensionFundDetailsRepository pensionFundDetailsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        if (!req.getRequestURI().equals("/4analysis/pensionfunds")) {
            resp.sendRedirect("http://localhost:8080/4analysis/pensionfunds");
            return;
        }

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
