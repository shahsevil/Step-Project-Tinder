package services;

import DAO.LikeDAO;
import DAO.UserDAO;
import entities.Like;
import entities.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserService {

    LikeDAO likeDAO = new LikeDAO();
    private UserDAO userDAO= new UserDAO();

    public List<User> listOfLikedUsers(int id) {
        List<Like> allById = likeDAO.getAllById(id);
        return userDAO.getAll().stream()
                .filter(user -> allById.contains(new Like(id, user.getUserId(),true)))
                .collect(Collectors.toList());
    }
}
