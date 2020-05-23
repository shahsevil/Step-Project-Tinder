package org.services;

import org.DAO.UserDAO;
import org.entities.User;

public class RegisterService {
  private static final UserDAO userDAO = new UserDAO();

  public void register(User user) {
    userDAO.insert(user);
  }
}
