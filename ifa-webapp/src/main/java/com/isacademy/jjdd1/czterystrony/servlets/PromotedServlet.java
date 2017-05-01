package com.isacademy.jjdd1.czterystrony.servlets;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

@WebServlet(urlPatterns = "/4analysis/promoted")
public class PromotedServlet extends HttpServlet {

//    @Inject
//    PromotedInvestFundRepository repository;
//
//    @Inject
//    Validator validator;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String investFundId = req.getParameter("investFundId");
//        String priority = req.getParameter("priority");
//
//        if (Objects.isNull(investFundId) || Objects.isNull(priority)) {
//            req.getRequestDispatcher("/promotion.jsp").forward(req,resp);
//            return;
//        }
//
//        int priorityInt = Integer.parseInt(priority);
//        PromotedInvestFund promotedInvestFund = new PromotedInvestFund(investFundId, priorityInt);
//
//        //walidacja
//        Set<ConstraintViolation<PromotedInvestFund>> errors = validator.validate(promotedInvestFund);
//        if (!errors.isEmpty()) {
//            resp.sendError(400, "Niepoprawne parametry: " + errors.iterator().next().getMessage());
//            return;
//        }
//
//        repository.add(promotedInvestFund);
//        req.getRequestDispatcher("/promotion.jsp").forward(req,resp);
//    }
}
