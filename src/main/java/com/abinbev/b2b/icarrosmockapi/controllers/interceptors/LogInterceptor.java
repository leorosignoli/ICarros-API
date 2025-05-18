package com.abinbev.b2b.icarrosmockapi.controllers.interceptors;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class LogInterceptor extends OncePerRequestFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

  public static final String TRACE_ID = "traceId";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      MDC.put(TRACE_ID, request.getHeader(TRACE_ID));

      LOGGER.debug("Received Request for: {}", request.getRequestURI());

      filterChain.doFilter(request, response);
    } finally {
      MDC.remove(TRACE_ID);
    }
  }
}
