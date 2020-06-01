package org.tinder_proj.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class GetReqData {

  public static Optional<Cookie> getCookie(HttpServletRequest req, String cookieName) {
    return Arrays.stream(req.getCookies())
            .filter(cookie -> cookieName.equals(cookie.getName()))
            .findFirst();
  }

  public static String getCookieValue(Optional<Cookie> cookie) {
    if (cookie.isPresent()) return cookie.get().getValue();
    else throw new IllegalArgumentException("Cookie is empty!!!");
  }
}