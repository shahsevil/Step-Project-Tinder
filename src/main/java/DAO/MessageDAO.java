package DAO;

import connection.ConnectionDB;
import entities.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MessageDAO implements DAO<Message> {

    private int fromId;
    private int toId;
    List<Message> messageList;

    public MessageDAO(int fromId, int toId, List<Message> messageList) {
        this.fromId = fromId;
        this.toId = toId;
        this.messageList = messageList;
    }

    @Override
    public Optional<Message> get(int id) {
        Message message = null;
        String SQL = "SELECT * FROM messages WHERE message_id = ?";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                message = new Message(resultSet.getInt("fromId"), resultSet.getInt("toId"),
                        resultSet.getString("content"), resultSet.getString("dateString"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
        return Optional.ofNullable(message);
    }

    @Override
    public Collection<Message> getAll() {
        String SQL = "SELECT * FROM messages where fromId = ? and receiver = ?";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, fromId);
            stmt.setInt(2, toId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                messageList.add(new Message(resultSet.getInt("fromId"),
                        resultSet.getInt("toId"),
                        resultSet.getString("content"), resultSet.getString("dateString")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
        return messageList;
    }

    @Override
    public void insert(Message message) {
        String SQL = "INSERT  INTO messages where fromId=? and toId=? and content=? and dateString=?";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, message.getFromUserId());
            stmt.setInt(2, message.getToUserId());
            stmt.setString(3, message.getContent());
            stmt.setString(4, message.getDateString());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public void delete(Message message) {
    }

    @Override
    public void update(Message message) {
    }
}
