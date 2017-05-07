package com.isacademy.jjdd1.czterystrony.filters;

import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/4analysis/notowania/*", "/4analysis/analiza/*"})
public class InvestFundIdFilter implements Filter {

    @Inject
    InvestFundRepository repository;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (!fundIdIsValid(httpServletRequest)) {
            httpServletResponse.sendRedirect("http://localhost:8080/4analysis");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean fundIdIsValid(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();

        if (Objects.nonNull(pathInfo)) {
            String investFundId = request.getPathInfo().substring(1);
            return Objects.nonNull(repository.getById(investFundId));
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
