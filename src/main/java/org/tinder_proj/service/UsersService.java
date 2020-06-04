package org.tinder_proj.service;

import org.tinder_proj.dao.DAOLike;
import org.tinder_proj.dao.DAOUser;
import org.tinder_proj.entity.Like;
import org.tinder_proj.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsersService {
  private final DAOUser DAO_USER;
  private final DAOLike DAO_LIKE;

  public UsersService(DAOUser DAO_USER, DAOLike DAO_LIKE) {
    this.DAO_USER = DAO_USER;
    this.DAO_LIKE = DAO_LIKE;
  }

  public List<User> getLikedUsers(int who_id) {
    String SQL = String.format("SELECT * FROM likes l WHERE l.who_id == %d && l.reaction == 'TRUE'", who_id);

    return DAO_LIKE.getBy(SQL).stream()
            .map(Like::getWhom_id)
            .map(DAO_USER::get)
            .map(Optional::get)
            .collect(Collectors.toList());
  }
}
