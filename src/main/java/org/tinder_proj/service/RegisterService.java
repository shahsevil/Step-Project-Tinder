package org.tinder_proj.service;

import org.tinder_proj.dao.DAOUser;
import org.tinder_proj.entity.User;

public class RegisterService {
    private final DAOUser DAO_USER;

    public RegisterService(DAOUser DAO_USER) {
        this.DAO_USER = DAO_USER;
    }

    public boolean isRegistrable(String username) {
        String SQL = String.format("SELECT * FROM users WHERE username = '%s';", username);
        return DAO_USER.getBy(SQL).size() == 0;
    }

    public void register(User user) {
        DAO_USER.insert(user);
    }
}
