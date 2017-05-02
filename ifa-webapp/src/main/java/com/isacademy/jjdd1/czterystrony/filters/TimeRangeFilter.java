package com.isacademy.jjdd1.czterystrony.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/4analysis/notowania/*", "/4analysis/analiza/*"})
public class TimeRangeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (!timeRangeIsValid(httpServletRequest)) {
            httpServletResponse.sendRedirect(httpServletRequest.getRequestURL().toString());
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean timeRangeIsValid(HttpServletRequest request) {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        return dateIsValid(from) && dateIsValid(to);
    }

    private boolean dateIsValid(String date) {
        if (!Objects.isNull(date) && !date.isEmpty()) {
            try {
                LocalDate.parse(date);
            } catch (DateTimeParseException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
