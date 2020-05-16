package services;

import DAO.LikeDAO;
import DAO.UserDAO;
import entities.Like;
import entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    LikeDAO likeDAO;
    private UserDAO userDAO= new UserDAO();



    public List<User> listOfLikedUsers(int id) {
        List<Like> likedUserIds = likeDAO.getAll().stream().filter(like -> like.getLikerUserId()==id)
                .collect(Collectors.toList());
        return userDAO.getAll().stream()
                .filter(user -> likedUserIds.contains(new Like(id, user.getUserId(),true)))
                .collect(Collectors.toList());
    }
}
