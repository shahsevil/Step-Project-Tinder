package DAO;

import connection.ConnectionDB;
import entities.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class LikeDAO implements DAO<Like> {

    private List<Like> likeList;
    private int likerUserId;

    public LikeDAO(List<Like> likeList, int likerUserId) {
        this.likeList = likeList;
        this.likerUserId = likerUserId;
    }

    @Override
    public Optional<Like> get(int id) {
        Like like = null;
        String SQL = "SELECT * FROM likes where likerUserId = ? and likedUserId = ?";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stm = connection.prepareStatement(SQL);
            stm.setInt(1, likerUserId);
            stm.setInt(2, id);
            stm.execute();
            ResultSet rset = stm.executeQuery();
            if (rset.next()) {
                int userId = rset.getInt("likerUserId");
                int liked_uid = rset.getInt("likedUserId");
                like = new Like(userId, liked_uid);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
        return Optional.ofNullable(like);
    }

    @Override
    public Collection<Like> getAll() {
        String SQL = "SELECT * FROM likes where likerUserId=?";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, likerUserId);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                Like like = new Like(rset.getInt("likerUserId"), rset.getInt("likedUserId"));
                likeList.add(like);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
        return likeList;
    }

    @Override
    public void insert(Like like) {
        String SQL = "INSERT INTO likes where likerUserId=? and likedUserId=?";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stm = connection.prepareStatement(SQL);
            stm.setInt(1, likerUserId);
            stm.setInt(2, like.getLikedUserId());
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
    }


    @Override
    public void update(Like like) {
    }

    @Override
    public void delete(Like like) {
    }
}
