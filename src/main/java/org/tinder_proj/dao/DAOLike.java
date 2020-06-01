package org.tinder_proj.dao;

import lombok.SneakyThrows;
import org.tinder_proj.entity.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DAOLike implements DAO<Like> {
  private final String SQL_getAll = "SELECT * FROM likes";
  private final String SQL_getBy = "SELECT * FROM likes WHERE id = ?";
  private final String SQL_insert = "INSERT INTO likes (who_id, whom_id, action) VALUES (?, ? , ?)";
  private final String SQL_delete = "DELETE FROM likes WHERE id = ?";
  private final String SQL_update = "UPDATE likes SET who_id = ?, whom_id = ?, action = ? WHERE id = ?";
  private final Connection CONN;

  public DAOLike(Connection connection) {
    this.CONN = connection;
  }

  @SneakyThrows
  @Override
  public List<Like> getAll() {
    PreparedStatement stmt = CONN.prepareStatement(SQL_getAll);
    ResultSet resultSet = stmt.executeQuery();
    List<Like> data = new ArrayList<>();
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      int who_id = resultSet.getInt("who_id");
      int whom_id = resultSet.getInt("whom_id");
      boolean action = resultSet.getBoolean("action");
      data.add(new Like(id, who_id, whom_id, action));
    }
    return data;
  }

  @SneakyThrows
  @Override
  public List<Like> getBy(Predicate<Like> p) {
    return getAll().stream().filter(p).collect(Collectors.toList());
  }

  @SneakyThrows
  @Override
  public Optional<Like> get(int id) {
    PreparedStatement stmt = CONN.prepareStatement(SQL_getBy);
    stmt.setInt(1, id);
    ResultSet resultSet = stmt.executeQuery();
    return !resultSet.next() ? Optional.empty() : Optional.of(
            new Like(
                    resultSet.getInt("id"),
                    resultSet.getInt("who_id"),
                    resultSet.getInt("whom_id"),
                    resultSet.getBoolean("action")
            )
    );
  }

  @SneakyThrows
  @Override
  public void insert(Like like) {
    PreparedStatement stmt = CONN.prepareStatement(SQL_insert);
    stmt.setInt(1, like.getWho_id());
    stmt.setInt(2, like.getWhom_id());
    stmt.setBoolean(3, like.isReaction());
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
  public void update(Like like) {
    PreparedStatement stmt = CONN.prepareStatement(SQL_update);
    stmt.setInt(1, like.getWho_id());
    stmt.setInt(2, like.getWhom_id());
    stmt.setBoolean(3, like.isReaction());
    stmt.setBoolean(3, like.isReaction());
    stmt.setInt(4, like.getId());
    stmt.executeUpdate();
  }
}
