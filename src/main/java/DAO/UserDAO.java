package DAO;

import connection.DBConnection;
import entities.User;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public class UserDAO implements DAO<User> {
  @Override
  public Optional<User> get(int id) {
    try {
      Connection conn = DBConnection.getConnection();
      conn.setAutoCommit(false);

//      String SQL = "SELECT * FROM users u WHERE u.id = ?";
//      PreparedStatement stmt = conn.prepareStatement(SQL);
//      stmt.setString(1, String.valueOf(id));
//      ResultSet resultSet = stmt.executeQuery();
//      while (resultSet.next()) {
//        String name = resultSet.getString("name");
//        String surname = resultSet.getString("surname");
//        String username = resultSet.getString("username");
//        int gender = resultSet.getInt("gender");
//        String photoUrl = resultSet.getString("photoUrl");
      return Optional.empty();

    } catch (URISyntaxException ex) {
      throw new RuntimeException("URISyntaxException - dao:user:get", ex);
    } catch (SQLException ex) {
      throw new RuntimeException("SQL - dao:user:get", ex);
    }
  }

  @Override
  public Collection<User> getAllBy(Predicate<User> p) {
    return null;
  }

  @Override
  public Collection<User> getAll() {
    return null;
  }

  @Override
  public void update(User user) {

  }

  @Override
  public void insert(User user) {

  }

  @Override
  public void delete(User user) {

  }
}
