package org.tinder_proj.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tinder_proj.dao.DAOLike;
import org.tinder_proj.dao.DAOUser;
import org.tinder_proj.entity.User;
import org.tinder_proj.service.UsersService;
import org.tinder_proj.utils.TemplateEngine;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.tinder_proj.utils.Converters.strToInt;
import static org.tinder_proj.utils.GetReqData.getCookie;
import static org.tinder_proj.utils.GetReqData.getCookieValue;

public class UserServlet extends HttpServlet {
    private final UsersService USERS_SERVICE;
    private final TemplateEngine TEMPLATE_ENGINE;
    private static final Logger log =
            LogManager.getFormatterLogger(UserServlet.class);

    public UserServlet(DAOUser DAO_USER, DAOLike DAO_LIKE, TemplateEngine templateEngine) {
        this.USERS_SERVICE = new UsersService(DAO_USER, DAO_LIKE);
        this.TEMPLATE_ENGINE = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Cookie> whoCookie = getCookie(req, "who_id");
        int who_id = strToInt(getCookieValue(whoCookie));

        List<User> likedUsers = USERS_SERVICE.getLikedUsers(who_id);

        if (likedUsers.size() != 0) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("listOfLikedUsers", likedUsers);
            TEMPLATE_ENGINE.render("people-list.ftl", hashMap, resp);
        } else {
            try (PrintWriter w = resp.getWriter()) {
                w.write("You have not liked anybody yet");
                log.info("You have not liked anybody yet");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");

        if ("sendMessage".equals(action)) {
            try {
                Optional<Cookie> whomCookie = getCookie(req, "whom_id");
                int whom_id = strToInt(getCookieValue(whomCookie));
                resp.sendRedirect(String.format("/message/%d", whom_id));
            } catch (Exception e) {
                log.error("Exception caught! Redirected to /users...");
                resp.sendRedirect("/users");
            }
        } else {
            Cookie[] cookies = req.getCookies();
            Arrays.stream(cookies)
                    .forEach(c -> {
                        c.setMaxAge(0);
                        resp.addCookie(c);
                    });
            try {
                resp.sendRedirect("/*");
            } catch (IOException e) {
                log.error("Exception caught!!!");
            }
        }
    }
}