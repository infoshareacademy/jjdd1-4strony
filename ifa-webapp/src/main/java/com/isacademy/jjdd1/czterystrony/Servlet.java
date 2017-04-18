package com.isacademy.jjdd1.czterystrony;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
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
import java.util.Map;

@WebServlet(urlPatterns = "/")
public class Servlet extends HttpServlet {

//    @Inject
//    Tutaj wtrzykniemy którąś z istniejących klas

    @Inject
    InvestFundsDaoTxt investFundsDaoTxt;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //a tutaj ją wykorzystamy

//        Map<String, String> list = investFundsDaoTxt.ratingsDataFileToInvestFundName();

        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

//        list.entrySet().forEach(s -> writer.append(s.getKey() + " | " + s.getValue() + "\n"));

        List<InvestFund> investFunds = investFundsDaoTxt.getAllByName();

        investFunds.stream().forEach(s -> writer.append(s.toString() + "\n"));

//        np. wyświetlając na ekran
//        usa.forEach(writer::println);
        writer.flush();
    }
}
