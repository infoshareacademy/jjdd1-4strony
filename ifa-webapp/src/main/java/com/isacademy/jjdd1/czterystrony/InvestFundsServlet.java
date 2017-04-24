package com.isacademy.jjdd1.czterystrony;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/investfunds")
public class InvestFundsServlet extends HttpServlet {

    private List<InvestFund> investFunds;

    @Inject
    InvestFundsDaoTxt investFundsDaoTxt;

    @Override
    public void init() throws ServletException {
        this.investFunds = investFundsDaoTxt.getAllByPriority();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        List<InvestFund> promotedInvestFunds = investFunds.stream()
                .filter(s -> s.getPriority() < 0)
                .collect(Collectors.toList());

//        List<List<String>> investFundsTable = investFunds.stream()
//                .map(s -> {
//                    Rating currentRating = s.getCurrentRating();
//                    return Arrays.asList(s.getName(), s.getId(), currentRating.getDate().toString(), currentRating.getCloseValue().toString());
//                })
//                .collect(Collectors.toList());
//
//        List<List<>> promotedInvestFunds = investFunds.stream()
//                .filter(s -> s.getPriority() > 0)
//                .map(s -> {
//                    Rating currentRating = s.getCurrentRating();
//                    return Arrays.asList(s.getName(), s.getId(), currentRating.getDate().toString(), currentRating.getCloseValue().toString());
//                })
//                .collect(Collectors.toList());

        req.setAttribute("investFunds", investFunds);
        req.setAttribute("promotedInvestFunds", promotedInvestFunds);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }

}
