package DAO;

import connection.ConnectionDB;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User> {

    List<User> userList = new ArrayList<>();

    @Override
    public Optional<User> get(int id) {
        User user = null;
        String SQLS = "SELECT * FROM users where id = ?";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stm = connection.prepareStatement(SQLS);
            stm.setInt(1, id);
            stm.execute();
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                String profession = resultSet.getString("profession");
                String lastLogin = resultSet.getString("lastLogin");
                String urlPhoto = resultSet.getString("urlPhoto");
                user = new User(userName, password, profession, lastLogin, urlPhoto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong..");
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Collection<User> getAll() {
        User user;
        String SQL = "SELECT * FROM users";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                String profession = resultSet.getString("profession");
                String lastLogin = resultSet.getString("lastLogin");
                String urlPhoto = resultSet.getString("urlPhoto");
                user = new User(id, userName, password, profession, lastLogin, urlPhoto);
                userList.add(user);
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong..");
        }
        return userList;
    }

    @Override
    public void insert(User user) {
        User user1;
        String SQL = "INSERT INTO users where username=? and password=? and profession=? and lastLogin=? and urlPhoto=?";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getProfession());
            stmt.setString(4, user.getLastLogin());
            stmt.setString(5, user.getUrlPhoto());
            stmt.execute();
            user1 = new User(user.getUsername(), user.getPassword(), user.getProfession(), user.getLastLogin()
                    , user.getUrlPhoto());
            userList.add(user1);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong..");
        }
    }

    @Override
    public void update(User user) {
    }

    @Override
    public void delete(User user) {
    }
}
