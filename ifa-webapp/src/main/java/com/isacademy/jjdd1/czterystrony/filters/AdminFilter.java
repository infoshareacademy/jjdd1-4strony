package com.isacademy.jjdd1.czterystrony.filters;

import com.isacademy.jjdd1.czterystrony.users.SessionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/4analysis/raporty/*", "/4analysis/promocje"})
public class AdminFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AdminFilter.class);

    @Inject
    SessionData sessionData;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (!sessionData.isAdmin()) {
            log.info("User {} is not an administrator, redirecting...", sessionData.getUser().getEmail());
            httpServletResponse.sendRedirect("/");
            return;
        }

        log.debug("Administrator already logged in.");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
