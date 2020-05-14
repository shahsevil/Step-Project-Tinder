package servlets;

import entities.User;
import services.LogInOutService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.stream.Collectors;

public class LoginServlet extends HttpServlet {
    private LogInOutService logInOutService;

    public LoginServlet(LogInOutService logInOutService) {
        this.logInOutService = logInOutService;
    }

    public LoginServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = new BufferedReader(new FileReader(new File("./src/main/resources/content/login.html"))).lines().collect(Collectors.joining("\n"));
        try (PrintWriter w = resp.getWriter()) {
            w.write(result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username= req.getParameter("username");
        String password= req.getParameter("password");
        try {
            /**
             *
             */
        } catch (Exception e) {
            resp.sendRedirect("/login");
        }
    }
}
