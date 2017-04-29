package com.isacademy.jjdd1.czterystrony;

import com.isacademy.jjdd1.czterystrony.model.PromotedInvestFund;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/4analysis/promoted")
public class PromotedServlet extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String investFundId = req.getParameter("investFundId");
        int priority = Integer.parseInt(req.getParameter("priority"));

        PromotedInvestFund promotedInvestFund = new PromotedInvestFund(investFundId, priority);

        em.persist(promotedInvestFund);

        em.createQuery("SELECT i FROM PromotedInvestFund i").getResultList().forEach(System.out::println);
    }
}
