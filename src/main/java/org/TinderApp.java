package org;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.servlets.*;

public class TinderApp {
  public static void main(String[] args) throws Exception {
    int port;
    try {
      port = Integer.parseInt(System.getenv("PORT"));
    } catch (NumberFormatException ex) {
      port = 5000;
    }
    Server server = new Server(port);
    ServletContextHandler handler = new ServletContextHandler();
    TemplateEngine templateEngine = new TemplateEngine("./src/main/resources/content/ftl/");

    handler.addServlet(new ServletHolder(new StaticServlet("css")), "/css/*");

    handler.addServlet(new ServletHolder(new FirstPageServlet()), "/*");
    handler.addServlet(new ServletHolder(new RegisterServlet()), "/register");
    handler.addServlet(new ServletHolder(new LoginServlet()), "/login");
    handler.addServlet(new ServletHolder(new LikePageServlet(templateEngine)), "/users");
    handler.addServlet(new ServletHolder(new UserServlet(templateEngine)), "/liked");
    handler.addServlet(new ServletHolder(new MessageServlet(templateEngine)), "/message/*");
    handler.addServlet(new ServletHolder(new LogoutServlet()), "/logout");

    server.setHandler(handler);
    server.start();
    server.join();
  }
}