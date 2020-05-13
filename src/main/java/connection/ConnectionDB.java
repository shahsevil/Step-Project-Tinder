package connection;

import java.net.URISyntaxException;
import java.sql.*;

public class ConnectionDB {
  //  public static Connection getConnection() throws URISyntaxException, SQLException {
  public static void main(String[] args) throws URISyntaxException, SQLException {
    String dbURL = System.getenv("JDBC_DATABASE_URL");
    Connection conn = DriverManager.getConnection(dbURL, "elvintaghiyev14", "elvin123db@");
  }
}