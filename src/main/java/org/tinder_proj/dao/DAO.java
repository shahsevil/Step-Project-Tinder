package org.tinder_proj.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface DAO<A> {
  List<A> getAll();
  List<A> getBy(Predicate<A> p);
  Optional<A> get(int id);
  void insert(A a);
  void delete(int id);
  void update(A a);
}
