package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDB {
  private static final String host = "ec2-54-197-48-79.compute-1.amazonaws.com";
  private static final int port = 5432;
  private static final String dbname = "daal1n6dluih5b";
  private static final String username = "aektxckrvsyfpl";
  private static final String password = "dc9622d44c714737a69b25c17d2a7ee5f0e730823f6a824fcb1026ad582e2a2b";
  private static final String JDBC_DATABASE_URL =
          String.format("jdbc:postgresql://%s:%d/%s?user=%s&password=%s",
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
    return DriverManager.getConnection(JDBC_DATABASE_URL);
  }
}