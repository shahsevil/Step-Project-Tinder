package org.services;

import org.DAO.LikeDAO;
import org.DAO.UserDAO;
import org.entities.Like;
import org.entities.User;
import org.filter.LikeFilter;

import java.util.List;

public class LikePageService {

  private static final UserDAO USER_DAO = new UserDAO();
  private static final LikeDAO LIKE_DAO = new LikeDAO();
  private LikeFilter likeFilter = new LikeFilter();


  public List<User> getUsersToShow(int id) {
    return USER_DAO.getUsersToShow(id);
  }

  public void addReaction(int who_id, int whom_id, boolean action) {
    if (!likeFilter.isReactedBefore(new Like(who_id, whom_id, action)))
      LIKE_DAO.insert(new Like(who_id, whom_id, action));
    else LIKE_DAO.update(new Like(who_id, whom_id, action));
  }
}
