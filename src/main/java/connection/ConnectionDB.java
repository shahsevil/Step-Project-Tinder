package connection;

import java.net.URISyntaxException;
import java.sql.*;

public class ConnectionDB {

    public static void main(String[] args) throws SQLException {
//        String dbURL = System.getenv("JDBC_DATABASE_URL");
        String dbURL = "jdbc:postgresql://ec2-54-247-78-30.eu-west-1.compute.amazonaws.com:5432/d3du1hdp316o87?user=eyscvazigqvthg&password=88a9e297385cc9f450652971aed96ba6972540ba9bf2f2b9dec40859564ae142";
        System.out.println(dbURL);
        ResultSet r = DriverManager.getConnection(dbURL).prepareStatement("SELECT * FROM users").executeQuery();
        r.next();
        System.out.println(r.getString("name"));

    }

    public static Connection getConnection() {
        return null;
    }
}