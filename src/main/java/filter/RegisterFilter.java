package filter;

import DAO.UserDAO;

public class RegisterFilter {
    private UserDAO userDAO= new UserDAO();

    public boolean isUniqueUsername(String username){
        return userDAO.getAllByName(username).isPresent();
    }
}
