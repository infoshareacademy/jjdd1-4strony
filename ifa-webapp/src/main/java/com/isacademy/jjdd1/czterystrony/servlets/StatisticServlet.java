package com.isacademy.jjdd1.czterystrony.servlets;

import com.isacademy.jjdd1.czterystrony.model.InvestFundDetails;
import com.isacademy.jjdd1.czterystrony.model.StatisticDetails;
import com.isacademy.jjdd1.czterystrony.repositories.StatisticDetailsRepository;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/4analysis/statistic")

public class StatisticServlet extends HttpServlet {

    @Inject
    StatisticDetailsRepository statisticDetailsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        List<StatisticDetails> statisticDetails = statisticDetailsRepository.getAll();

        RequestDispatcher dispatcher = req.getRequestDispatcher("/statistic.jsp");
        req.setAttribute("statisticsDetails", statisticDetails);
        dispatcher.forward(req, resp);
    }


}
