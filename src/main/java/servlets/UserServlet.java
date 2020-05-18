package servlets;

import entities.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UserServlet extends HttpServlet {
    TemplateEngine templateEngine;
    UserService userService = new UserService();

    public UserServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int who_id = getIdFromCookie(req);
        List<User> list = userService.listOfLikedUsers(who_id);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("listOfLikedUsers", list);
        templateEngine.render("people-list.ftl", hashMap, resp);
    }

    public static int getIdFromCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        int id = 0;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("who_id")) {
                    id = Integer.parseInt(c.getValue());
                }
            }
        }
        return id;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try{
           int id = getIdFromCookie(req);
           resp.sendRedirect(String.format("/message/%d",id));
       } catch (Exception e) {
           resp.sendRedirect("/users");
       }
    }
}
