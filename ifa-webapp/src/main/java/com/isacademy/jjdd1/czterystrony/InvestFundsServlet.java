package com.isacademy.jjdd1.czterystrony;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        List<InvestFund> promotedInvestFunds = new ArrayList<>();
        List<InvestFund> otherInvestFunds = new ArrayList<>();

        investFunds.forEach(s -> {
            if (s.getPriority() < 0) {
                promotedInvestFunds.add(s);
            } else {
                otherInvestFunds.add(s);
            }
        });

        req.setAttribute("promotedInvestFunds", promotedInvestFunds);
        req.setAttribute("otherInvestFunds", otherInvestFunds);
        req.setAttribute("allInvestFunds", investFunds);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }

}
