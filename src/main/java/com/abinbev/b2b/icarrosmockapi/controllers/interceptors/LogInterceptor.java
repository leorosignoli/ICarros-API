package com.abinbev.b2b.icarrosmockapi.controllers.interceptors;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class LogInterceptor extends OncePerRequestFilter {

  public static final String TRACE_ID = "traceId";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      MDC.put(TRACE_ID, request.getHeader(TRACE_ID));
      filterChain.doFilter(request, response);
    } finally {
      MDC.remove(TRACE_ID);
    }
  }
}
