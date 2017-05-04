//package com.isacademy.jjdd1.czterystrony;
//
//import com.isacademy.jjdd1.czterystrony.model.InvestFundStatistics;
//import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
//import com.isacademy.jjdd1.czterystrony.instruments.Rating;
//import com.isacademy.jjdd1.czterystrony.analysis.LocalExtremaProvider;
//import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
//@WebServlet(urlPatterns = "/4analysis/analiza/*")
//public class AnalysisServlet extends HttpServlet {
//
//    private InvestFund investFund;
//    private List<Rating> ratings;
//    private TimeRange timeRange;
//    private LocalExtremaProvider localExtremaProvider;
//    private int zigZag;
//    private static EntityManager entityManager;
//    private InvestFundStatistics statistics;
//    @Inject
//    DaoService daoService;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisServlet.class);
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html;charset=UTF-8");
//        String investFundId = req.getPathInfo().substring(1);
//        statistics = new InvestFundStatistics();
//        statistics.setFund(investFundId);
//        Date date = new Date();
//        statistics.setDate(date);
//
//        insertStatistic(statistics);
//
//        setTimeRange(req);
//        setZigZag(req);
//
//        investFund = daoService.get(investFundId);
//        localExtremaProvider = new LocalExtremaProvider(investFund, timeRange);
//        ratings = localExtremaProvider.findExtrema(zigZag);
//
//        req.setAttribute("investFund", investFund);
//        req.setAttribute("ratings", ratings);
//        req.setAttribute("from", timeRange.getStart());
//        req.setAttribute("to", timeRange.getEnd());
//        req.setAttribute("zigZag", zigZag);
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/analysis.jsp");
//        dispatcher.forward(req, resp);
//    }
//
//    private void setTimeRange(HttpServletRequest req) {
//        String from = req.getParameter("from");
//        String to = req.getParameter("to");
//        LocalDate dateFrom = LocalDate.of(2000,1,1);
//        LocalDate dateTo = LocalDate.now();
//
//        if (!Objects.isNull(from) && !from.isEmpty()) {
//            dateFrom = LocalDate.parse(from);
//        }
//
//        if (!Objects.isNull(to) && !to.isEmpty()) {
//            dateTo = LocalDate.parse(to);
//        }
//
//        timeRange = new TimeRange(dateFrom, dateTo);
//        statistics.setDateFrom(dateFrom);
//        statistics.setDateTo(dateTo);
//        updateStatistic(statistics);
//    }
//
//    private void setZigZag(HttpServletRequest req) {
//        String zigZagReq = req.getParameter("zigZag");
//
//        if (!Objects.isNull(zigZagReq) && !zigZagReq.isEmpty()) {
//            zigZag = Integer.parseInt(zigZagReq);
//        } else {
//            zigZag = 0;
//        }
//        statistics.setZigZag(zigZag);
//        updateStatistic(statistics);
//    }
//
//    private void insertStatistic(InvestFundStatistics statistics) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
//        entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(statistics);
//        entityManager.getTransaction().commit();
//    }
//
//    private static void updateStatistic(InvestFundStatistics statistics) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
//        entityManager = entityManagerFactory.createEntityManager();
//
//
//        entityManager.getTransaction().begin();
//        entityManager.merge(statistics);
//        entityManager.getTransaction().commit();
//        LOGGER.info("Updated statistic: {}", statistics);
//    }
//}
