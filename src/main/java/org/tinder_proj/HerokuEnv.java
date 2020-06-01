package org.tinder_proj;

public class HerokuEnv {
  private static final String PORT = "PORT";
  private static final String EMPTY = " is empty!!!";
  private static final String JDBC_DATABASE_URL = "JDBC_DATABASE_URL";
  private static final String JDBC_DATABASE_USERNAME = "JDBC_DATABASE_USERNAME";
  private static final String JDBC_DATABASE_PASSWORD = "JDBC_DATABASE_PASSWORD";

  public static int port() {
    try {
      return Integer.parseInt(System.getenv(PORT));
    } catch (NumberFormatException ex) {
      return 5000;
    }
  }

  public static String jdbc_url() {
    String url = System.getenv(JDBC_DATABASE_URL);
    if (url == null) throw new IllegalArgumentException(JDBC_DATABASE_URL + EMPTY);
    return url;
  }

  public static String jdbc_username() {
    String url = System.getenv(JDBC_DATABASE_USERNAME);
    if (url == null) throw new IllegalArgumentException(JDBC_DATABASE_USERNAME + EMPTY);
    return url;
  }

  public static String jdbc_password() {
    String url = System.getenv(JDBC_DATABASE_PASSWORD);
    if (url == null) throw new IllegalArgumentException(JDBC_DATABASE_PASSWORD + EMPTY);
    return url;
  }
}
