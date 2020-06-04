package org.tinder_proj.filter;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieFilter implements HttpFilter {
  @Override
  public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
    for (Cookie c: request.getCookies()) {

    }
  }
}
