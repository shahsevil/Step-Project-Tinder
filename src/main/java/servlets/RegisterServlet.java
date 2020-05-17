package servlets;

import entities.User;
import services.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RegisterServlet extends HttpServlet {
  private static final String CONTENT_DIR = "./src/main/resources/content/";
  private static final RegisterService REGISTER_SERVICE = new RegisterService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try (OutputStream os = resp.getOutputStream()) {
      Files.copy(Paths.get(CONTENT_DIR, "register.html"), os);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    String profession = req.getParameter("profession");
    String photoUrl = req.getParameter("photoUrl");
    User newUser = new User(username, password, profession, photoUrl);
    REGISTER_SERVICE.register(newUser);
    resp.sendRedirect("/login");
  }
}