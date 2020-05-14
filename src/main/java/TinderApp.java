import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.FirstPageServlet;
import servlets.LoginServlet;
import servlets.RegisterServlet;
import servlets.StaticServlet;

public class TinderApp {
  public static void main(String[] args) throws Exception {
    Server server = new Server(9000);
    ServletContextHandler handler = new ServletContextHandler();

    handler.addServlet(new ServletHolder(new FirstPageServlet()), "/*");
    handler.addServlet(new ServletHolder(new RegisterServlet()), "/register");
    handler.addServlet(new ServletHolder(new LoginServlet()),  "/login");
    handler.addServlet(new ServletHolder(new StaticServlet("css")), "/css/*");

    server.setHandler(handler);
    server.start();
    server.join();
  }
}