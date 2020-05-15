package services;

import DAO.UserDAO;
import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private UserDAO userDAO= new UserDAO();

    public List<User> gelAll(){
        try {
            return new ArrayList<>(userDAO.getAll());
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    public Optional<User> getUserById(int id){
        try {
            return userDAO.get(id);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong");
        }
    }


}
