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

        List<InvestFund> otherInvestFunds = investFunds.stream()
                .filter(s -> s.getPriority() == 0)
                .collect(Collectors.toList());

        req.setAttribute("promotedInvestFunds", promotedInvestFunds);
        req.setAttribute("otherInvestFunds", otherInvestFunds);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }

}
