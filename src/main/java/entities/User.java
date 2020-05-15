package entities;

import java.text.SimpleDateFormat;

public class User {

  private int userId;
  private String username;
  private String password;
  private String profession;
  private long date;
  private String lastLogin;
  private String urlPhoto;

  public User(int userId, String username, String password, String profession, long date, String lastLogin, String urlPhoto) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.profession = profession;
    this.date = date;
    this.lastLogin = lastLogin;
    this.urlPhoto = urlPhoto;
  }

  public User(int userId, String username, String password, String profession, String lastLogin, String urlPhoto) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.profession = profession;
    this.lastLogin = lastLogin;
    this.urlPhoto = urlPhoto;
  }

  public User(String username, String password, String profession, String urlPhoto) {
    this.username = username;
    this.password = password;
    this.profession = profession;
    this.urlPhoto = urlPhoto;
  }

  public User(String username, String password, String profession, String lastLogin, String urlPhoto) {
    this.username = username;
    this.password = password;
    this.profession = profession;
    this.lastLogin = lastLogin;
    this.urlPhoto = urlPhoto;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public int getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getProfession() {
    return profession;
  }

  public long getDate() {
    return date;
  }

  public String getLastLogin() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    return sdf.format(date);
  }

  public String getUrlPhoto() {
    return urlPhoto;
  }
}
