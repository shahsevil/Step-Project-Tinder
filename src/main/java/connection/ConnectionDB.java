package connection;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class ConnectionDB {
  //  public static Connection getConnection() throws URISyntaxException, SQLException {
  public static void main(String[] args) throws URISyntaxException, SQLException {
    String dbURL = System.getenv("jdbc:postgresql://ec2-54-247-78-30.eu-west-1.compute.amazonaws.com:5432/d3du1hdp316o87");
    Connection conn = DriverManager.getConnection(dbURL);
  }
}