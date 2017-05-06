package com.isacademy.jjdd1.czterystrony.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/4analysis/investfundjson")
public class InvestFund2JsonServlet extends HttpServlet {

    @Inject
    InvestFundRepository investFundRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String investFundId = req.getParameter("id");

        if (Objects.nonNull(investFundId)) {
            InvestFund investFund = investFundRepository.getById(investFundId);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getOutputStream(), investFund);
        }
    }
}
