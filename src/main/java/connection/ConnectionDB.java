package connection;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class ConnectionDB {
  //  public static Connection getConnection() throws URISyntaxException, SQLException {
  public static void main(String[] args) throws URISyntaxException, SQLException {
    URI dbUri = new URI(System.getenv("postgres://eyscvazigqvthg:88a9e297385cc9f450652971aed96ba6972540ba9bf2f2b9dec40859564ae142@ec2-54-247-78-30.eu-west-1.compute.amazonaws.com:5432/d3du1hdp316o87"));
    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
    Connection conn = DriverManager.getConnection(dbUrl, username, password);
    String SQL = "Select * from users";
    PreparedStatement stmt = conn.prepareStatement(SQL);
    ResultSet rSet = stmt.executeQuery();
    System.out.println(rSet.getInt("name"));
  }
}