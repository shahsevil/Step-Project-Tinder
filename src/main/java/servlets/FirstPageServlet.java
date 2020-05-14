package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FirstPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String result = new BufferedReader(new FileReader(new File("./src/main/resources/content/first-page.html"))).lines().collect(Collectors.joining("\n"));
    try (PrintWriter w = resp.getWriter()) {
      w.write(result);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String next = req.getParameter("action");
    if (next.equals("login")) {
      try (OutputStream os = resp.getOutputStream()) {
        Files.copy(Paths.get("./src/main/resources/content/", "login.html"), os);
      }
    } else if (next.equals("register")) {
      try (OutputStream os = resp.getOutputStream()) {
        Files.copy(Paths.get("./src/main/resources/content/", "register.html"), os);
      }
    }
  }
}