package services;

import DAO.LikeDAO;
import DAO.UserDAO;
import entities.Like;
import entities.User;

import java.util.List;

public class LikePageService {
  private static final UserDAO USER_DAO = new UserDAO();
  private static final LikeDAO LIKE_DAO = new LikeDAO();

  public List<User> getUsersToShow(int id) {
    return USER_DAO.getUsersToShow(id);
  }

  public void addReaction(int who_id, int whom_id, boolean action) {
    LIKE_DAO.insert(new Like(who_id, whom_id, action));
  }
}
