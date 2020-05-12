package entities;

public class User {

    private int userId;
    private String name;
    private String surname;
    private String userName;
    private String password;
    private String gender;
    private String urlPhoto;

    public User(int userId, String name, String surname, String userName, String password, String gender, String urlPhoto) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.urlPhoto = urlPhoto;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    @Override
    public String toString() {
        return String.format("User{userId=%d, name='%s', surname='%s', userName='%s', password='%s', gender='%s', urlPhoto='%s'}",
                userId, name, surname, userName, password, gender, urlPhoto);
    }
}
