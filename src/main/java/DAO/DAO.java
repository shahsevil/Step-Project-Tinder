package DAO;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public interface DAO<A> {
    Optional<A> get(int id);
    Collection<A> getAll();
    void update(A a);
    void insert(A a);
    void delete(A a);
}
