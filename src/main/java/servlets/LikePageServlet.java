package servlets;

import services.LikePageService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LikePageServlet extends HttpServlet {
  private static final LikePageService LIKE_PAGE_SERVICE = new LikePageService();
  private final TemplateEngine templateEngine;

  public LikePageServlet(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    
  }
}