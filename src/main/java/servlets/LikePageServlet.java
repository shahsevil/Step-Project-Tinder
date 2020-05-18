package servlets;

import entities.User;
import services.LikePageService;

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

    User user0 = allUsers.get(0);

    HashMap<String, Object> data = new HashMap<>();
    data.put("username", user0.getUsername());
    data.put("photoUrl", user0.getUrlPhoto());

    Arrays.stream(cookies)
            .forEach(c -> {
              c.setMaxAge(0);
              resp.addCookie(c);
            });

    Cookie whom = new Cookie("whom_id", String.valueOf(user0.getUserId()));

    resp.addCookie(whom);
    templateEngine.render("like-page.ftl", data, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String reqParam = req.getParameter("reaction");
    boolean reaction = "like".equals(reqParam);
    Cookie[] cookies = req.getCookies();
    int who_id = 0;
    int whom_id = 0;

    for (Cookie c : cookies) {
      if ("who_id".equals(c.getName())) who_id = Integer.parseInt(c.getValue());
      else if ("whom_id".equals(c.getName())) whom_id = Integer.parseInt(c.getValue());
    }

    LIKE_PAGE_SERVICE.addReaction(who_id, whom_id, reaction);

    if (counter == allUsers.size()) resp.sendRedirect("/liked");
    else {
      User user = allUsers.get(counter++);

      HashMap<String, Object> data = new HashMap<>();
      data.put("username", user.getUsername());
      data.put("photoUrl", user.getUrlPhoto());

      Arrays.stream(cookies)
              .forEach(c -> {
                c.setMaxAge(0);
                resp.addCookie(c);
              });

      Cookie whom = new Cookie("whom_id", String.valueOf(user.getUserId()));

      resp.addCookie(whom);
      templateEngine.render("like-page.ftl", data, resp);
    }
  }
}