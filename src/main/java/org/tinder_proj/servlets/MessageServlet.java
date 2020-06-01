package org.tinder_proj.servlets;

import org.tinder_proj.dao.DAOMessage;
import org.tinder_proj.dao.DAOUser;
import org.tinder_proj.entity.Message;
import org.tinder_proj.entity.User;
import org.tinder_proj.service.MessageService;
import org.tinder_proj.utils.TemplateEngine;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.tinder_proj.utils.Converters.strToInt;
import static org.tinder_proj.utils.GetReqData.getCookie;
import static org.tinder_proj.utils.GetReqData.getCookieValue;

public class MessageServlet extends HttpServlet {
  private final MessageService MESSAGE_SERVICE;
  private final TemplateEngine TEMPLATE_ENGINE;

  public MessageServlet(DAOUser DAO_USER, DAOMessage DAO_MESSAGE, TemplateEngine templateEngine) {
    this.MESSAGE_SERVICE = new MessageService(DAO_USER, DAO_MESSAGE);
    this.TEMPLATE_ENGINE = templateEngine;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    Optional<Cookie> whoCookie = getCookie(req, "who_id");
    Optional<Cookie> whomCookie = getCookie(req, "whom_id");

    int who_id = strToInt(getCookieValue(whoCookie));
    int whom_id = strToInt(getCookieValue(whomCookie));

    List<Message> messages = MESSAGE_SERVICE.getMessages(who_id, whom_id);

    User sender = MESSAGE_SERVICE.getUserInfo(who_id);
    User receiver = MESSAGE_SERVICE.getUserInfo(whom_id);

    HashMap<String, Object> data = new HashMap<>();

    data.put("receiver_name", receiver.getUsername());
    data.put("receiver_photo_url", receiver.getPhoto_url());
    data.put("messages", messages);
    data.put("receiver_id", receiver.getId());

    TEMPLATE_ENGINE.render("chat.ftl", data, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Optional<Cookie> whoCookie = getCookie(req, "who_id");
    Optional<Cookie> whomCookie = getCookie(req, "whom_id");

    int from_id = strToInt(getCookieValue(whoCookie));
    int to_id = strToInt(getCookieValue(whomCookie));

    String message = req.getParameter("message");
    MESSAGE_SERVICE.addMessage(from_id, to_id, message, LocalDate.now());

    resp.sendRedirect(String.format("/message/%d", to_id));
  }
}
