package services;

import DAO.UserDAO;
import entities.User;

public class LogInOutService {

    private UserDAO userDAO;
    private boolean isLoggedIn;
    private boolean isLoggedOut;

    public LogInOutService(UserDAO users, boolean isLogged) {
        this.userDAO = new UserDAO();
        this.isLoggedIn = false;
        this.isLoggedOut=false;
    }

    public boolean isUserLoggedIn(User user){
        for (User u:userDAO.getAll()){
            if(u==user){
                isLoggedIn=true;
                return isLoggedIn;
            }
        }
        throw new RuntimeException("Something went wrong");
    }

    public boolean isUserLoggedOut(User user){
        for (User u:userDAO.getAll()){
            if(u==user){
                isLoggedOut=true;
                return isLoggedOut;
            }
        }
        throw new RuntimeException("Something went wrong");
    }

    public boolean isLoggedInSuccessful(){
        return isLoggedIn;
    }

    public boolean isLoggedOutSuccessful(){
        return isLoggedOut;
    }
}
