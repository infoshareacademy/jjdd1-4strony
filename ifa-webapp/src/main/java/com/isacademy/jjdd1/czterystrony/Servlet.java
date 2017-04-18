package com.isacademy.jjdd1.czterystrony;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class Servlet extends HttpServlet {

    @Inject
    InvestFundsDaoTxt investFundsDaoTxt;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<InvestFund> investFunds = investFundsDaoTxt.getAllByName();

        investFunds.stream().forEach(writer::println);

        writer.flush();
    }
}
