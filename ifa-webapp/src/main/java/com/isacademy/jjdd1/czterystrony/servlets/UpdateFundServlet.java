package com.isacademy.jjdd1.czterystrony.servlets;

import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/4analysis/updatefund")
public class UpdateFundServlet extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(UpdateFundServlet.class);

    @Inject
    InvestFundRepository investFundRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        List<InvestFund> investFunds = investFundRepository.getAll();
        req.setAttribute("investFunds", investFunds);
        req.getRequestDispatcher("/updatefund.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        int priority = Integer.parseInt(req.getParameter("priority"));

        InvestFund investFund = investFundRepository.getById(id);
        investFund.setPriority(priority);
        investFund.setName(name);
        log.info("Updated priority: {} for fund {}", priority, id);
        log.info("Updated name: {} for fund {}", name, id);
        investFundRepository.update(investFund);
        doGet(req, resp);
    }
}
