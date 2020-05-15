package services;

import DAO.UserDAO;
import entities.User;

public class LoginService {

    private UserDAO userDAO= new UserDAO();

    public int isUserLoggedIn(User user){
        for (User u:userDAO.getAll()){
            if(u==user){
                return u.getUserId();
            }
        }
        throw new RuntimeException("Something went wrong");
    }
}
