package org.tinder_proj.servlets;

import org.tinder_proj.dao.DAOLike;
import org.tinder_proj.dao.DAOUser;
import org.tinder_proj.entity.User;
import org.tinder_proj.service.LikePageService;
import org.tinder_proj.utils.TemplateEngine;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.tinder_proj.utils.Converters.intToStr;
import static org.tinder_proj.utils.Converters.strToInt;
import static org.tinder_proj.utils.GetReqData.getCookie;
import static org.tinder_proj.utils.GetReqData.getCookieValue;

public class LikePageServlet extends HttpServlet {
  private final LikePageService LIKE_PAGE_SERVICE;
  private final TemplateEngine TEMPLATE_ENGINE;
  private static List<User> userList;

  public LikePageServlet(DAOUser DAO_USER, DAOLike DAO_LIKE, TemplateEngine templateEngine) {
    LIKE_PAGE_SERVICE = new LikePageService(DAO_USER, DAO_LIKE);
    this.TEMPLATE_ENGINE = templateEngine;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    Optional<Cookie> whoCookie = getCookie(req, "who_id");

    int who_id = strToInt(getCookieValue(whoCookie));

    userList = LIKE_PAGE_SERVICE.getUsersExceptThis(who_id);

    if (userList.size() != 0) {
      User user = userList.get(0);

      HashMap<String, Object> data = new HashMap<>();
      data.put("username", user.getUsername());
      data.put("photoUrl", user.getPhoto_url());

      Cookie whom = new Cookie("whom_id", intToStr(user.getId()));
      Cookie index = new Cookie("index", "0");

      resp.addCookie(whom);
      resp.addCookie(index);
      TEMPLATE_ENGINE.render("like-page.ftl", data, resp);
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

    Optional<Cookie> whoCookie = getCookie(req, "who_id");
    Optional<Cookie> whomCookie = getCookie(req, "whom_id");
    Optional<Cookie> idxCookie = getCookie(req, "index");

    int who_id = strToInt(getCookieValue(whoCookie));
    int whom_id = strToInt(getCookieValue(whomCookie));
    int index = strToInt(getCookieValue(idxCookie));

    LIKE_PAGE_SERVICE.addReaction(who_id, whom_id, reaction);

    if (index != userList.size() - 1) {
      index += 1;
      User nextUser = userList.get(index);

      HashMap<String, Object> data = new HashMap<>();
      data.put("username", nextUser.getUsername());
      data.put("photoUrl", nextUser.getPhoto_url());

      whomCookie.get().setValue(intToStr(nextUser.getId()));
      idxCookie.get().setValue(intToStr(index));

      resp.addCookie(whomCookie.get());
      resp.addCookie(idxCookie.get());
      TEMPLATE_ENGINE.render("like-page.ftl", data, resp);
    } else resp.sendRedirect("/likes");
  }
}