package com.isacademy.jjdd1.czterystrony.servlets;

import com.isacademy.jjdd1.czterystrony.model.InvestFundDetails;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundDetailsRepository;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/4analysis/notowania/*", "/4analysis/analiza/*"})
public class InvestFundServlet extends HttpServlet {

    @Inject
    InvestFundDetailsRepository investFundDetailsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String investFundId = req.getPathInfo().substring(1);
        InvestFundDetails investFundDetails = investFundDetailsRepository.getById(investFundId);

        RequestDispatcher dispatcher;
        if (req.getRequestURI().contains("analiza")) {
            req.setAttribute("zigZag", 0);
            dispatcher = req.getRequestDispatcher("/analysis.jsp");
        } else {
            dispatcher = req.getRequestDispatcher("/fund.jsp");
        }

        req.setAttribute("investFund", investFundDetails);
        dispatcher.forward(req, resp);
    }
}
