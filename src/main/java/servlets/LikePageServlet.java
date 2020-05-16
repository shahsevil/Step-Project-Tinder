package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LikePageServlet extends HttpServlet {
  private final TemplateEngine templateEngine;

  public LikePageServlet(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//    templateEngine.render("like-page.ftl", , resp);
  }
}