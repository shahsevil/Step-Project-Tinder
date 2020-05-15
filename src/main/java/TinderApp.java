import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

public class TinderApp {
  public static void main(String[] args) throws Exception {
    Server server = new Server(9000);
    ServletContextHandler handler = new ServletContextHandler();
  //  TemplateEngine templateEngine = new TemplateEngine("./src/main/resources/content/ftl/*");

    handler.addServlet(new ServletHolder(new FirstPageServlet()), "/*");
    handler.addServlet(new ServletHolder(new RegisterServlet()), "/register");
    handler.addServlet(new ServletHolder(new StaticServlet("css")), "/css/*");
    handler.addServlet(new ServletHolder(new LoginServlet()),"/login");

    server.setHandler(handler);
    server.start();
    server.join();
  }
}