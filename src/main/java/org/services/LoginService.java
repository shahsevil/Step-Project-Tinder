package org.services;

import org.DAO.UserDAO;
import org.entities.User;

public class LoginService {

    private UserDAO userDAO= new UserDAO();

    public int userId(User user){
        for (User u:userDAO.getAll()){
            if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())){
                return u.getUserId();
            }
        }
        throw new RuntimeException("Something went wrong");
    }

    public User login(String username, String password) {
        return userDAO.getAllByNameAndPass(username, password);
    }

}
