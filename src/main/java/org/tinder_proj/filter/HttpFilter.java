package org.tinder_proj.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

interface HttpFilter extends Filter {
  default void init(FilterConfig filterConfig) {
  }

  void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

  default void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    if (request instanceof HttpServletRequest) {
      doHttpFilter(
              (HttpServletRequest) request,
              (HttpServletResponse) response,
              chain);
    } else {
      chain.doFilter(request, response);
    }
  }

  default void destroy() {
  }
}
