package DAO;

import connection.ConnectionDB;
import entities.Message;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MessageDAO implements DAO<Message> {

    private int from_id;
    private int to_id;
    List<Message> messageList;

    public MessageDAO() {
    }

    public MessageDAO(int from_id, int to_id, List<Message> messageList) {
        this.from_id = from_id;
        this.to_id = to_id;
        this.messageList = messageList;
    }

    @Override
    public Optional<Message> get(int id) {
        Message message = null;
        String SQL = "SELECT * FROM messages WHERE id = ?";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                message = new Message(resultSet.getInt("from_id"), resultSet.getInt("to_id"),
                        resultSet.getString("content"), resultSet.getString("date"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
        return Optional.ofNullable(message);
    }

    @Override
    public Collection<Message> getAll() {
        String SQL = "SELECT * FROM messages where from_id = ? and to_id = ?";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, from_id);
            stmt.setInt(2, to_id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                messageList.add(new Message(resultSet.getInt("from_id"),
                        resultSet.getInt("to_id"),
                        resultSet.getString("content"), resultSet.getString("date")));
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
        return messageList;
    }

    @Override
    public void insert(Message message) {
        String SQL = "INSERT INTO messages (from_id,to_id,content) VALUES (?,?,?)";
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, message.getFromUserId());
            stmt.setInt(2, message.getToUserId());
            stmt.setString(3, message.getContent());
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    public List<Message> getMessages(int from, int to) {
        String SQL = "SELECT * FROM messages where from_id = ? and to_id = ? OR from_id = ? and to_id = ? ORDER BY date";
        List<Message> messages = new ArrayList<>();
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, from);
            stmt.setInt(2, to);
            stmt.setInt(3, to);
            stmt.setInt(4, from);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                messages.add(new Message(resultSet.getInt("from_id"),
                        resultSet.getInt("to_id"),
                        resultSet.getString("content"), resultSet.getString("date")));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");
        }
        return messages;
    }

}
