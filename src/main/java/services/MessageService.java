package services;

import DAO.MessageDAO;
import DAO.UserDAO;
import entities.Message;
import entities.User;

import java.util.List;

public class MessageService {
  private static final MessageDAO MESSAGE_DAO = new MessageDAO();
  private static final UserDAO USER_DAO = new UserDAO();

  public List<Message> getMessages(int from_id, int to_id, int limit) {
    return MESSAGE_DAO.getMessages(from_id, to_id, limit);
  }

  public User getUserInfo(int u_id) {
    return USER_DAO.get(u_id).orElseThrow(RuntimeException::new);
  }
}
