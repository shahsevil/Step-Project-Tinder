package org.tinder_proj.service;

import org.tinder_proj.dao.DAOMessage;
import org.tinder_proj.dao.DAOUser;
import org.tinder_proj.entity.Message;
import org.tinder_proj.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MessageService {
  private final DAOUser DAO_USER;
  private final DAOMessage DAO_MESSAGE;

  public MessageService(DAOUser DAO_USER, DAOMessage DAO_MESSAGE) {
    this.DAO_USER = DAO_USER;
    this.DAO_MESSAGE = DAO_MESSAGE;
  }

  public List<Message> getMessages(int who_id, int whom_id) {
    return DAO_MESSAGE.getMessages(who_id, whom_id);
  }

  public User getUserInfo(int id) {
    Optional<User> user = DAO_USER.get(id);
    if (user.isPresent()) return user.get();
    throw new IllegalArgumentException("Something went wrong while getting user from db!!!");
  }

  public void addMessage(int who_id, int whom_id, String content, LocalDate date) {
    DAO_MESSAGE.insert(new Message(who_id, whom_id, content, date));
  }
}
