package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FirstPageServlet extends HttpServlet {
  private static final String CONTENT_DIR = "./src/main/resources/content/";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try (OutputStream os = resp.getOutputStream()) {
      Files.copy(Paths.get(CONTENT_DIR, "first-page.html"), os);
    }
  }
}