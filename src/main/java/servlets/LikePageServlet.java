package servlets;

import entities.User;
import services.LikePageService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LikePageServlet extends HttpServlet {
  private static final LikePageService LIKE_PAGE_SERVICE = new LikePageService();
  private final TemplateEngine templateEngine;
  private List<User> all;
  private static int counter = 0;

  public LikePageServlet(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    Cookie[] cookies = req.getCookies();
    Cookie who_cookie = null;
    if (cookies.length != 0) {
       who_cookie = Arrays.stream(cookies)
              .filter(cookie -> cookie.getName().equals("who_id"))
              .findFirst()
              .orElse(new Cookie("who_id", "1"));
    }

    int who_id = Integer.parseInt(who_cookie.getValue());
    all = LIKE_PAGE_SERVICE.getUsersToShow(who_id);

    User user = all.get(counter++);

    HashMap<String, Object> data = new HashMap<>();
    data.put("username", user.getUsername());
    data.put("photoUrl", user.getUrlPhoto());

    if (counter > 1) {
      boolean action = req.getParameter("action").equals("like");
      LIKE_PAGE_SERVICE.addLike(who_id, who_id, action);

      // removing cookies
      Arrays.stream(cookies)
              .forEach(c -> {
                c.setMaxAge(0);
                resp.addCookie(c);
              });

      // adding new Cookies
      resp.addCookie(new Cookie("who_id", String.valueOf(who_id)));
      resp.addCookie(new Cookie("whom_id", String.valueOf(user.getUserId())));
    }

    templateEngine.render("like-page.ftl", data, resp);
    throw new IllegalArgumentException("Not finished yet...");
  }
}