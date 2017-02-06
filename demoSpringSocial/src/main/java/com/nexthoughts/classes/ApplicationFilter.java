package com.nexthoughts.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

public class ApplicationFilter implements Filter {
    final private Logger logger = LoggerFactory.getLogger(ApplicationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("Dated: " + new Date() + "  ************************    Request Params: " + request.getParameterMap().toString());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}