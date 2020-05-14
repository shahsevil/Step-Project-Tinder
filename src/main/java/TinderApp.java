import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.FirstPageServlet;

public class TinderApp {
  public static void main(String[] args) throws Exception {
    Server server = new Server(9000);
    ServletContextHandler handler = new ServletContextHandler();

    handler.addServlet(new ServletHolder(new FirstPageServlet()), "/*");

    server.setHandler(handler);
    server.start();
    server.join();
  }
}
