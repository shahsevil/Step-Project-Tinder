package org.tinder_proj.service;

import org.tinder_proj.dao.DAOLike;
import org.tinder_proj.dao.DAOUser;
import org.tinder_proj.entity.Like;
import org.tinder_proj.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LikePageService {
  private final DAOUser DAO_USER;
  private final DAOLike DAO_LIKE;

  public LikePageService(DAOUser DAO_USER, DAOLike DAO_LIKE) {
    this.DAO_USER = DAO_USER;
    this.DAO_LIKE = DAO_LIKE;
  }


  public List<User> getUsersExceptThis(int id) {
    return DAO_USER.getAll().stream().filter(user -> user.getId() != id).collect(Collectors.toList());
  }

  public void addReaction(int who_id, int whom_id, boolean reaction) {
    Optional<Like> isReactedBefore = DAO_LIKE.getAll()
            .stream()
            .filter(like -> like.getWho_id() == who_id
                    && like.getWhom_id() == whom_id)
            .findFirst();

    if (isReactedBefore.isPresent()) {
      Like like = isReactedBefore.get();
      like.setReaction(reaction);
      DAO_LIKE.update(like);
    } else DAO_LIKE.insert(new Like(who_id, whom_id, reaction));
  }
}
