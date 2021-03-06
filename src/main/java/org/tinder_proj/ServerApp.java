package org.tinder_proj;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.tinder_proj.dao.DAOLike;
import org.tinder_proj.dao.DAOMessage;
import org.tinder_proj.dao.DAOUser;
import org.tinder_proj.db.DbConn;
import org.tinder_proj.db.DbSetup;
import org.tinder_proj.filter.CookieFilter;
import org.tinder_proj.servlets.*;
import org.tinder_proj.utils.TemplateEngine;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;

import static org.tinder_proj.utils.Dirs.FREEMARKER_DIR;

public class ServerApp {

    public static void main(String[] args) throws Exception {

        Connection conn = DbConn.create(HerokuEnv.jdbc_url(), HerokuEnv.jdbc_username(), HerokuEnv.jdbc_password());
        DbSetup.migrate(HerokuEnv.jdbc_url(), HerokuEnv.jdbc_username(), HerokuEnv.jdbc_password());

        final DAOUser DAO_USER = new DAOUser(conn);
        final DAOLike DAO_LIKE = new DAOLike(conn);
        final DAOMessage DAO_MESSAGE = new DAOMessage(conn);
        final TemplateEngine TEMPLATE_ENGINE = new TemplateEngine(FREEMARKER_DIR);

        Server server = new Server(HerokuEnv.port());
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new StaticServlet("css")), "/css/*");

        handler.addServlet((FirstPageServlet.class), "/*");
        handler.addServlet(new ServletHolder(new RegisterServlet(DAO_USER)), "/register");
        handler.addServlet(new ServletHolder(new LoginServlet(DAO_USER)), "/login");
        handler.addServlet(new ServletHolder(new LikePageServlet(DAO_USER, DAO_LIKE, TEMPLATE_ENGINE)), "/users");
        handler.addServlet(new ServletHolder(new UserServlet(DAO_USER, DAO_LIKE, TEMPLATE_ENGINE)), "/likes");
        handler.addServlet(new ServletHolder(new MessageServlet(DAO_USER, DAO_MESSAGE, TEMPLATE_ENGINE)), "/message/*");

        handler.addFilter(CookieFilter.class, "/likes", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(CookieFilter.class, "/users", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(CookieFilter.class, "/message/*", EnumSet.of(DispatcherType.REQUEST));

        server.setHandler(handler);
        server.start();
        server.join();
    }
}