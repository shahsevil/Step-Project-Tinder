package servlets;

import services.LoginService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    LoginService loginService;
    TemplateEngine templateEngine;
    UserService userService= new UserService();

    public UserServlet(TemplateEngine templateEngine) {
        this.templateEngine=templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
