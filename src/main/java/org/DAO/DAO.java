package org.DAO;

import java.util.Collection;
import java.util.Optional;

public interface DAO<A> {
    Optional<A> get(int id);
    Collection<A> getAll();
    void insert(A a);
}
