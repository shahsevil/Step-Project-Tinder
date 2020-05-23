package org.services;

import org.DAO.LikeDAO;
import org.DAO.UserDAO;
import org.entities.Like;
import org.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    LikeDAO likeDAO = new LikeDAO();
    private UserDAO userDAO= new UserDAO();

    public List<User> listOfLikedUsers(int id) {
        List<Like> allById = likeDAO.getAllById(id);
        return userDAO.getAll().stream()
                .filter(user -> allById.contains(new Like(id, user.getUserId(),true)))
                .collect(Collectors.toList());
    }

    public List<User> getLikedUsers(int who_id) {
        return likeDAO.getAllById(who_id).stream()
                .map(Like::getLikedUserId)
                .map(id -> userDAO.get(id).orElseThrow(RuntimeException::new))
                .collect(Collectors.toList());
    }
}
