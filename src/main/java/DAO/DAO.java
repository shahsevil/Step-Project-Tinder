package DAO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public interface DAO<A> {
    Optional<A> get(int id) throws SQLException;
    Collection<A> getAllBy(Predicate<A> p);
    Collection<A> getAll();
    void update(A a);
    void insert(A a);
    void delete(A a);
}
