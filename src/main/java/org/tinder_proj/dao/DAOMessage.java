package org.tinder_proj.dao;

import lombok.SneakyThrows;
import org.tinder_proj.entity.Message;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DAOMessage implements DAO<Message> {
  private final String SQL_getAll = "SELECT * FROM messages";
  private final String SQL_getBy = "SELECT * FROM messages WHERE id = ?";
  private final String SQL_insert = "INSERT INTO messages (from_id, to_id, content, date) VALUES (?, ?, ?, ?)";
  private final String SQL_delete = "DELETE FROM messages WHERE id = ?";
  private final String SQL_update = "UPDATE messages SET from_id = ?, to_id = ?, content = ?, date = ? WHERE id = ?";
  private final Connection CONN;

  public DAOMessage(Connection connection) {
    this.CONN = connection;
  }


  @SneakyThrows
  @Override
  public List<Message> getAll() {
    PreparedStatement stmt = CONN.prepareStatement(SQL_getAll);
    ResultSet resultSet = stmt.executeQuery();
    List<Message> data = new ArrayList<>();
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      int from_id = resultSet.getInt("from_id");
      int to_id = resultSet.getInt("to_id");
      String content = resultSet.getString("content");
      Date date = resultSet.getDate("date");
      data.add(new Message(id, from_id, to_id, content, LocalDate.parse(String.valueOf(date))));
    }
    return data;
  }

  @Override
  public List<Message> getBy(Predicate<Message> p) {
    return getAll().stream().filter(p).collect(Collectors.toList());
  }

  @SneakyThrows
  @Override
  public Optional<Message> get(int id) {
    PreparedStatement stmt = CONN.prepareStatement(SQL_getBy);
    stmt.setInt(1, id);
    ResultSet resultSet = stmt.executeQuery();
    return !resultSet.next() ? Optional.empty() : Optional.of(
            new Message(
                    resultSet.getInt("id"),
                    resultSet.getInt("from_id"),
                    resultSet.getInt("to_id"),
                    resultSet.getString("content"),
                    LocalDate.parse(String.valueOf(resultSet.getDate("date")))
            )
    );
  }

  @SneakyThrows
  @Override
  public void insert(Message message) {
    PreparedStatement stmt = CONN.prepareStatement(SQL_insert);
    stmt.setInt(1, message.getFrom_id());
    stmt.setInt(2, message.getTo_id());
    stmt.setString(3, message.getContent());
    stmt.setDate(4, Date.valueOf(message.getDate()));
    stmt.executeUpdate();
  }

  @SneakyThrows
  @Override
  public void delete(int id) {
    PreparedStatement stmt = CONN.prepareStatement(SQL_delete);
    stmt.setInt(1, id);
    stmt.executeUpdate();
  }

  @SneakyThrows
  @Override
  public void update(Message message) {
    PreparedStatement stmt = CONN.prepareStatement(SQL_update);
    stmt.setInt(1, message.getFrom_id());
    stmt.setInt(2, message.getTo_id());
    stmt.setString(3, message.getContent());
    stmt.setDate(4, Date.valueOf(message.getDate()));
    stmt.executeUpdate();
  }

  public List<Message> getMessages(int from, int to) {
    String SQL = "SELECT * FROM messages where from_id = ? and to_id = ? OR from_id = ? and to_id = ? ORDER BY date";
    List<Message> messages = new ArrayList<>();
    try {
      PreparedStatement stmt = CONN.prepareStatement(SQL);
      stmt.setInt(1, from);
      stmt.setInt(2, to);
      stmt.setInt(3, to);
      stmt.setInt(4, from);
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        messages.add(new Message(resultSet.getInt("from_id"),
                resultSet.getInt("to_id"),
                resultSet.getString("content"),
                LocalDate.parse(String.valueOf(resultSet.getDate("date")))));
      }
    } catch (SQLException e) {
      throw new RuntimeException("Something went wrong");
    }
    return messages;
  }

}
