package com.isacademy.jjdd1.czterystrony.servlets;

import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class HomePageServlet extends HttpServlet {

//    @Inject
//    DaoService daoService;

    @EJB
    InvestFundRepository investFundRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        if (!req.getRequestURI().equals("/4analysis")) {
            resp.sendRedirect("http://localhost:8080/4analysis");
            return;
        }

        List<Object[]> promotedInvestFunds = investFundRepository.getPromotedWithCurrentRating();
        List<Object[]> otherInvestFunds = investFundRepository.getNotPromotedWithCurrentRating();
        List<Object[]> allInvestFundsByName = investFundRepository.getAllWithCurrentRating();

        boolean dataFound = true;
        
//        try {
//            investFunds.forEach(s -> {
//                allInvestFundsByName.add(s);
//                if (s.getPriority() < 0) {
//                    promotedInvestFunds.add(s);
//                } else {
//                    otherInvestFunds.add(s);
//                }
//            });
//            allInvestFundsByName.sort(Comparator.comparing(InvestFund::getName));
//            dataFound = true;
//        } catch (Exception e) {
//            dataFound = false;
//        }

        req.setAttribute("promotedInvestFunds", promotedInvestFunds);
        req.setAttribute("otherInvestFunds", otherInvestFunds);
        req.setAttribute("allInvestFunds", allInvestFundsByName);
        req.setAttribute("dataFound", dataFound);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }

}
