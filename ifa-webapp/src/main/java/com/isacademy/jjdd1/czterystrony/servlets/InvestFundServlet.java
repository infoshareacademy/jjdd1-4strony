package com.isacademy.jjdd1.czterystrony.servlets;

import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.InvestFundDetails;
import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;
import com.isacademy.jjdd1.czterystrony.model.Rating;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundDetailsRepository;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;
import com.isacademy.jjdd1.czterystrony.repositories.RatingRepository;
import com.isacademy.jjdd1.czterystrony.technicalanalysis.LocalExtremaProvider;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = {"/4analysis/notowania/*", "/4analysis/analiza/*"})
public class InvestFundServlet extends HttpServlet {

    @Inject
    InvestFundDetailsRepository investFundDetailsRepository;

    @Inject
    InvestFundRepository investFundRepository;

    @Inject
    RatingRepository ratingRepository;

    @Inject
    LocalExtremaProvider localExtremaProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String investFundId = req.getPathInfo().substring(1);
        InvestFundDetails investFundDetails = investFundDetailsRepository.getById(investFundId);
        InvestFund investFund = investFundRepository.getById(investFundId);
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        LocalDate dateFrom;
        LocalDate dateTo;

        if (Objects.isNull(from) || from.isEmpty() || Objects.isNull(to) || to.isEmpty()) {
            dateFrom = ratingRepository.getOldestForFund(investFund).getDate();
            dateTo = ratingRepository.getNewestForFund(investFund).getDate();
        } else {
            dateFrom = LocalDate.parse(req.getParameter("from"));
            dateTo = LocalDate.parse(req.getParameter("to"));
        }

        TimeRange timeRange = new TimeRange(dateFrom, dateTo);
        List<Rating> ratings = ratingRepository.getByFundInTimeRange(investFund, timeRange);

        RequestDispatcher dispatcher;
        if (req.getRequestURI().contains("analiza")) {
            String zigZagReq = req.getParameter("zigZag");
            int zigZag = 0;
            if (!Objects.isNull(zigZagReq) && !zigZagReq.isEmpty()) {
                zigZag = Integer.parseInt(zigZagReq);
            }

            ratings = localExtremaProvider.findExtrema(investFund, timeRange, zigZag);
            req.setAttribute("zigZag", zigZag);
            dispatcher = req.getRequestDispatcher("/analysis.jsp");
        } else {
            dispatcher = req.getRequestDispatcher("/fund.jsp");
        }

        req.setAttribute("investFund", investFundDetails);
        req.setAttribute("ratings", ratings);
        req.setAttribute("from", timeRange.getStart());
        req.setAttribute("to", timeRange.getEnd());

        dispatcher.forward(req, resp);
    }
}
