package DAO;

import connection.ConnectionDB;
import entities.Like;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class LikeDAO implements DAO<Like> {

    private List<Like> likeList;
    private int liker_id;

    public LikeDAO(List<Like> likeList, int likerUserId) {
        this.likeList = likeList;
        this.liker_id = likerUserId;
    }

    public LikeDAO() {

    }

    @Override
    public Optional<Like> get(int id) {
        Like like = null;
        String SQL = "SELECT * FROM likes where who_id = ? and whom_id = ?";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stm = connection.prepareStatement(SQL);
            stm.setInt(1, liker_id);
            stm.setInt(2, id);
            stm.execute();
            ResultSet rset = stm.executeQuery();
            if (rset.next()) {
                int userId = rset.getInt("liker_id");
                int likedUserId = rset.getInt("liked_id");
                boolean action=rset.getBoolean("action");
                like = new Like(userId, likedUserId,action);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
        return Optional.ofNullable(like);
    }

    @Override
    public Collection<Like> getAll() {
        String SQL = "SELECT * FROM likes";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                Like like = new Like(rset.getInt("who_id"), rset.getInt("whom_id"),
                        rset.getBoolean("action"));
                likeList.add(like);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
        return likeList;
    }

    @Override
    public void insert(Like like) {
        String SQL = "INSERT INTO likes(who_id,whom_id,action) VALUES (?,?,?)";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stm = connection.prepareStatement(SQL);
            stm.setInt(1, like.getLikerUserId());
            stm.setInt(2, like.getLikedUserId());
            stm.setBoolean(3,like.isAction());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    public List<Like> getAllById(int who_id) {
        String SQL = "SELECT * FROM likes where who_id = ?";
        ArrayList<Like> allLikes = new ArrayList<>();
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, who_id);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                allLikes.add(new Like(rset.getInt("who_id"),
                        rset.getInt("whom_id"),
                        rset.getBoolean("action")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
        return allLikes;
    }

    public ArrayList<User> getAllByIdJoinUser(int who_id) {
      String SQL = "SELECT * FROM users u LEFT JOIN likes l on u.id == ? AND u.id == l.who_id";
        ArrayList<User> allUsers = new ArrayList<>();
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, who_id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                String urlPhoto = resultSet.getString("photo_url");
                String profession = resultSet.getString("profession");
                String lastLogin = resultSet.getString("last_login");
                allUsers.add(new User(userId, userName, password, profession, lastLogin, urlPhoto));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
        return allUsers;
    }
}
