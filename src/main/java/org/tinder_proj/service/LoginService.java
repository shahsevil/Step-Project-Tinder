package org.tinder_proj.service;

import org.tinder_proj.dao.DAOUser;
import org.tinder_proj.entity.User;

import java.time.LocalDate;
import java.util.Optional;

public class LoginService {
  private final DAOUser DAO_USER;

  public LoginService(DAOUser DAO_USER) {
    this.DAO_USER = DAO_USER;
  }

  public Optional<User> isRegisteredUser(String username, String password) {
    return DAO_USER.getAll()
            .stream()
            .filter(user -> username.equals(user.getUsername()) && password.equals(user.getPassword()))
            .findFirst();
  }

  public void updateLastLoginDate(User user, LocalDate last_login) {
    user.setLast_login(last_login);
    DAO_USER.update(user);
  }
}
