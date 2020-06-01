package org.tinder_proj.servlets;

import org.tinder_proj.dao.DAOUser;
import org.tinder_proj.entity.User;
import org.tinder_proj.service.RegisterService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.tinder_proj.utils.Dirs.TEMPLATE_DIR;

public class RegisterServlet extends HttpServlet {
  private final RegisterService REGISTER_SERVICE;

  public RegisterServlet(DAOUser DAO_USER) {
    this.REGISTER_SERVICE = new RegisterService(DAO_USER);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try (OutputStream os = resp.getOutputStream()) {
      Files.copy(Paths.get(TEMPLATE_DIR, "register.html"), os);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    String profession = req.getParameter("profession");
    String photoUrl = req.getParameter("photoUrl");

    if (REGISTER_SERVICE.isRegistrable(username)) {
      REGISTER_SERVICE.register(new User(username, password, profession, photoUrl, LocalDate.now()));
      resp.sendRedirect("/login");
    } else resp.sendRedirect("/register");
  }
}
