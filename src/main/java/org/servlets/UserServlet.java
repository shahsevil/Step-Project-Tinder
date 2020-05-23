package org.servlets;

import org.entities.User;
import org.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UserServlet extends HttpServlet {
    UserService userService = new UserService();
    TemplateEngine templateEngine;

    public UserServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int who_id = getIdFromCookie(req, "who_id");
//        List<User> list = userService.listLikedUsers(who_id);

        List<User> likedUsers = userService.getLikedUsers(who_id);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("listOfLikedUsers", likedUsers);
        templateEngine.render("people-list.ftl", hashMap, resp);
    }

    public static int getIdFromCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        int id = 0;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (name.equals(c.getName())) {
                    id = Integer.parseInt(c.getValue());
                }
            }
        }
        return id;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       try{
           int id = getIdFromCookie(req, "whom_id");
           resp.sendRedirect(String.format("/message/%d",id));
       } catch (Exception e) {
           resp.sendRedirect("/users");
       }
    }
}
