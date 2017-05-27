package com.isacademy.jjdd1.czterystrony.servlets;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFundDetails;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundDetailsRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/4analysis/notowania/*")
public class FundRatingsServlet extends HttpServlet {

    @Inject
    InvestFundDetailsRepository investFundDetailsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String investFundId = req.getPathInfo().substring(1);
        InvestFundDetails investFundDetails = investFundDetailsRepository.getById(investFundId);
        req.setAttribute("investFund", investFundDetails);
        req.getRequestDispatcher("/fund.jsp").forward(req, resp);
    }
}
