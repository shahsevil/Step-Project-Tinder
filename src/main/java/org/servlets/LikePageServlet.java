package org.servlets;

import org.entities.User;
import org.services.LikePageService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LikePageServlet extends HttpServlet {
  private static final LikePageService LIKE_PAGE_SERVICE = new LikePageService();
  private final TemplateEngine templateEngine;
  private static List<User> allUsers;
  private static int counter = 1;

  public LikePageServlet(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    Cookie[] cookies = req.getCookies();
    Cookie cookie = Arrays.stream(cookies)
            .filter(c -> "who_id".equals(c.getName()))
            .findFirst()
            .orElseThrow(RuntimeException::new);

    allUsers = LIKE_PAGE_SERVICE.getUsersToShow(Integer.parseInt(cookie.getValue()));

    if (allUsers.get(0) != null) {
      User user0 = allUsers.get(0);

      HashMap<String, Object> data = new HashMap<>();
      data.put("username", user0.getUsername());
      data.put("photoUrl", user0.getUrlPhoto());

      Cookie whom = new Cookie("whom_id", String.valueOf(user0.getUserId()));
      Cookie index = new Cookie("index", "0");

      resp.addCookie(whom);
      resp.addCookie(index);
      templateEngine.render("like-page.ftl", data, resp);
    } else {
      try (PrintWriter w = resp.getWriter()) {
        w.write("No any user found!");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    boolean reaction = "like".equals(req.getParameter("reaction"));
    Cookie[] cookies = req.getCookies();
    Cookie whoCookie = null;
    Cookie whomCookie = null;
    Cookie index = null;

    for (Cookie c : cookies) {
      if ("who_id".equals(c.getName())) whoCookie = c;
      else if ("whom_id".equals(c.getName())) whomCookie = c;
      else if ("index".equals(c.getName())) index = c;
    }

    int who_id = Integer.parseInt(whoCookie.getValue());
    int whom_id = Integer.parseInt(whomCookie.getValue());
    int idx = Integer.parseInt(index.getValue());

    LIKE_PAGE_SERVICE.addReaction(who_id, whom_id, reaction);

    if (idx == allUsers.size()-1) resp.sendRedirect("/liked");
    else {
      idx += 1;
      User next_user = allUsers.get(idx);
      System.out.println("id:" + next_user.getUserId());

      HashMap<String, Object> data = new HashMap<>();
      data.put("username", next_user.getUsername());
      data.put("photoUrl", next_user.getUrlPhoto());

      whomCookie.setValue(String.valueOf(next_user.getUserId()));
      index.setValue(String.valueOf(idx));

      resp.addCookie(whomCookie);
      resp.addCookie(index);
      templateEngine.render("like-page.ftl", data, resp);
    }
  }
}