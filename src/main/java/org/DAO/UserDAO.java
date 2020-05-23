package org.DAO;

import org.connection.ConnectionDB;
import org.entities.User;

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
      ResultSet resultSet = stm.executeQuery();
      if (resultSet.next()) {
        int who_id = resultSet.getInt("id");
        String userName = resultSet.getString("username");
        String password = resultSet.getString("password");
        String urlPhoto = resultSet.getString("photo_url");
        String profession = resultSet.getString("profession");
        String lastLogin = resultSet.getString("last_login");
        user = new User(who_id, userName, password, profession, lastLogin, urlPhoto);
      }
      connection.close();
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
        String urlPhoto = resultSet.getString("photo_url");
        String profession = resultSet.getString("profession");
        String lastLogin = resultSet.getString("last_login");
        user = new User(id, userName, password, profession, lastLogin, urlPhoto);
        userList.add(user);
      }
      connection.close();
    } catch (Exception e) {
      throw new RuntimeException("Something went wrong..");
    }
    return userList;
  }

  @Override
  public void insert(User user) {
    User user1;
    String SQL = "INSERT INTO users(username,password,photo_url,profession) VALUES (?,?,?,?)";
    try {
      Connection connection = ConnectionDB.getConnection();
      PreparedStatement stmt = connection.prepareStatement(SQL);
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getUrlPhoto());
      stmt.setString(4, user.getProfession());
      stmt.executeUpdate();
      user1 = new User(user.getUsername(), user.getPassword(), user.getProfession(), user.getLastLogin()
              , user.getUrlPhoto());
      userList.add(user1);
      connection.close();
    } catch (Exception e) {
      throw new RuntimeException("Something went wrong..");
    }
  }

  public List<User> getUsersToShow(int id) {
    List<User> users = new ArrayList<>();
    try {
      String SQL = "SELECT * FROM users where id != ?";
      Connection conn = ConnectionDB.getConnection();
      PreparedStatement stmt = conn.prepareStatement(SQL);
      stmt.setInt(1, id);
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        int userId = resultSet.getInt("id");
        String userName = resultSet.getString("username");
        String password = resultSet.getString("password");
        String urlPhoto = resultSet.getString("photo_url");
        String profession = resultSet.getString("profession");
        String lastLogin = resultSet.getString("last_login");
        users.add(new User(userId, userName, password, profession, lastLogin, urlPhoto));
      }
      conn.close();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return users;
  }

  public User getAllByNameAndPass(String username, String password) {
    User user;
    String SQL = "SELECT * FROM users WHERE username = ? AND password = ?";
    try {
      Connection connection = ConnectionDB.getConnection();
      PreparedStatement stmt = connection.prepareStatement(SQL);
      stmt.setString(1, username);
      stmt.setString(2, password);
      ResultSet resultSet = stmt.executeQuery();
      resultSet.next();
      int id = resultSet.getInt("id");
      String userName = resultSet.getString("username");
      String pass = resultSet.getString("password");
      String urlPhoto = resultSet.getString("photo_url");
      String profession = resultSet.getString("profession");
      String lastLogin = resultSet.getString("last_login");
      user = new User(id, userName, pass, profession, lastLogin, urlPhoto);
      connection.close();
    } catch (Exception e) {
      throw new RuntimeException("Something went wrong..");
    }
    return user;
  }


  // for registering
  public Optional<User> getAllByName(String username) {
    String SQL = "SELECT * FROM users WHERE username = ?";
    try {
      Connection connection = ConnectionDB.getConnection();
      PreparedStatement stmt = connection.prepareStatement(SQL);
      stmt.setString(1, username);
      ResultSet resultSet = stmt.executeQuery();
      if (resultSet.next()) {
        int id = resultSet.getInt("id");
        String userName = resultSet.getString("username");
        String pass = resultSet.getString("password");
        String urlPhoto = resultSet.getString("photo_url");
        String profession = resultSet.getString("profession");
        String lastLogin = resultSet.getString("last_login");
        connection.close();
        return Optional.of(new User(id, userName, pass, profession, lastLogin, urlPhoto));
      }
    } catch (Exception e) {
      throw new RuntimeException("Something went wrong..");
    }
    return Optional.empty();
  }
}
