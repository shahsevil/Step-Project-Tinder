package DAO;

import entities.User;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public class UserDAO implements DAO<User> {
  @Override
  public Optional<User> get(int id) {
    return Optional.empty();
  }

  @Override
  public Collection<User> getAllBy(Predicate<User> p) {
    return null;
  }

  @Override
  public Collection<User> getAll() {
    return null;
  }

  @Override
  public void update(User user) {

  }

  @Override
  public void insert(User user) {

  }

  @Override
  public void delete(User user) {

  }
}
