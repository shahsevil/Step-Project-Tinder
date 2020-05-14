package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
  private static final String host = "ec2-54-247-78-30.eu-west-1.compute.amazonaws.com";
  private static final int port = 5432;
  private static final String dbname = "d3du1hdp316o87";
  private static final String username = "eyscvazigqvthg";
  private static final String password = "88a9e297385cc9f450652971aed96ba6972540ba9bf2f2b9dec40859564ae142";
  private static final String JDBC_DATABASE_URL = String.format("jdbc:postgresql://%s:%d/%s?user=%s&password=%s",
          host, port, dbname, username, password);

  /**
   * If system environmental variable configured successfully just write:
   * String dbURL = System.getenv("JDBC_DATABASE_URL");
   * <p>
   * JDBC_DATABASE_URL pattern is=> jdbc:postgresql://<host>:<port>/<dbname>?user=<username>&password=<password>
   * <p>
   *
   * @return Connection to database with 'JDBC_DATABASE_URL'
   * @throws SQLException for null dbUrl
   */
  public static Connection getConnection() throws SQLException {
    String dbUrl = System.getenv(JDBC_DATABASE_URL);
    return DriverManager.getConnection(dbUrl);
  }
}