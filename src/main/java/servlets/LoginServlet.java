package servlets;

import entities.User;
import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoginServlet extends HttpServlet {

    private static final String CONTENT_DIR = "./src/main/resources/content/";
    private LoginService loginService= new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (OutputStream os = resp.getOutputStream()) {
            Files.copy(Paths.get(CONTENT_DIR, "login.html"), os);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username= req.getParameter("username");
        String password= req.getParameter("password");
        try {
            User login = loginService.login(username, password);
            System.out.println(login.getUsername());
            resp.addCookie(new Cookie("who_id",String.valueOf(login.getUserId())));
            resp.sendRedirect("/users");
        } catch (Exception e) {
            resp.sendRedirect("/login");
        }
    }
}
