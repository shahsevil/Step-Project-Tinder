package services;

import connection.DBConnection;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ThisIsForTesting {
  public static void main(String[] args) throws URISyntaxException, SQLException {
    Connection conn = DBConnection.getConnection();
    String SQL = "INSERT INTO users(name, surname, username, password, gender, profession, last_login, photo_url)" +
            " VALUES(?,?,?,?,?,?,?)";
    PreparedStatement stmt = conn.prepareStatement(SQL);
    stmt.setString(1, "Elvin");
    stmt.setString(2, "Taghizade");
    stmt.setString(3, "elvintaghiyev184@gmail.com");
    stmt.setString(4, "qwerty");
    stmt.setString(5, "1");
    stmt.setString(6, "software developer");
    stmt.setString(6, "13/05/2020");
    stmt.setString(6, "www.google.com");
    stmt.executeQuery();
  }
}