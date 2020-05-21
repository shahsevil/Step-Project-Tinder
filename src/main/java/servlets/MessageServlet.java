package servlets;

import entities.Message;
import entities.User;
import services.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class MessageServlet extends HttpServlet {
  private static final MessageService MESSAGE_SERVICE = new MessageService();
  private final TemplateEngine templateEngine;

  public MessageServlet(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Cookie[] cookies = req.getCookies();
    Cookie whoCookie = null;
    Cookie whomCookie = null;

    for (Cookie c : cookies) {
      if ("who_id".equals(c.getName())) whoCookie = c;
      else if ("whom_id".equals(c.getName())) whomCookie = c;
    }

    int who_id = Integer.parseInt(whoCookie.getValue());
    int whom_id = Integer.parseInt(whomCookie.getValue());

    List<Message> messages = MESSAGE_SERVICE.getMessages(who_id, whom_id, 10);
    User sender = MESSAGE_SERVICE.getUserInfo(who_id);
    User receiver = MESSAGE_SERVICE.getUserInfo(whom_id);

    HashMap<String, Object> data = new HashMap<>();

    data.put("receiver_name", receiver.getUsername());
    data.put("sender_photo_url", sender.getUrlPhoto());
    data.put("messages", messages);
    data.put("receiver_id", receiver.getUserId());

    templateEngine.render("chat.ftl", data, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Cookie[] cookies = req.getCookies();
    Cookie whoCookie = null;
    Cookie whomCookie = null;

    for (Cookie c : cookies) {
      if ("who_id".equals(c.getName())) whoCookie = c;
      else if ("whom_id".equals(c.getName())) whomCookie = c;
    }

    int from_id = Integer.parseInt(whoCookie.getValue());
    int to_id = Integer.parseInt(whomCookie.getValue());

    String message = req.getParameter("message");
    MESSAGE_SERVICE.addMessage(from_id, to_id, message, LocalDate.now());

    resp.sendRedirect(String.format("/message/%d", to_id));
  }
}