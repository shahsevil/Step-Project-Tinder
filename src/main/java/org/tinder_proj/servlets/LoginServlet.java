package org.tinder_proj.servlets;

import org.tinder_proj.dao.DAOUser;
import org.tinder_proj.entity.User;
import org.tinder_proj.service.LoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;

import static org.tinder_proj.utils.Converters.intToStr;
import static org.tinder_proj.utils.Dirs.TEMPLATE_DIR;

public class LoginServlet extends HttpServlet {
  private final LoginService LOGIN_SERVICE;

  public LoginServlet(DAOUser DAO_USER) {
    this.LOGIN_SERVICE = new LoginService(DAO_USER);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try (OutputStream os = resp.getOutputStream()) {
      Files.copy(Paths.get(TEMPLATE_DIR, "login.html"), os);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    Optional<User> isRegisteredUser = LOGIN_SERVICE.isRegisteredUser(username, password);

    if (isRegisteredUser.isPresent()) {
      User user = isRegisteredUser.get();
      LOGIN_SERVICE.updateLastLoginDate(user, LocalDate.now());
      resp.addCookie(new Cookie("who_id", intToStr(user.getId())));
      resp.sendRedirect("/users");
    } else {
      resp.sendRedirect("/login");
    }
  }
}
