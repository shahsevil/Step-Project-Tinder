package servlets;

import entities.User;
import services.LikePageService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LikePageServlet extends HttpServlet {
  private static final LikePageService LIKE_PAGE_SERVICE = new LikePageService();
  private final TemplateEngine templateEngine;
  private static List<User> allUsers;

  public LikePageServlet(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Cookie[] cookies = req.getCookies();
    Cookie cookie = Arrays.stream(cookies)
            .filter(c -> "who_id".equals(c.getName()))
            .findFirst()
            .orElseThrow(RuntimeException::new);

    allUsers = LIKE_PAGE_SERVICE.getUsersToShow(Integer.parseInt(cookie.getValue()));

    HashMap<String, Object> data = new HashMap<>();
    data.put("username", allUsers.get(0).getUsername());
    data.put("photoUrl", allUsers.get(0).getUrlPhoto());

    templateEngine.render("like-page.ftl", data, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

  }
}