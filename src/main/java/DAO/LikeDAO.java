package DAO;

import entities.Like;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public class LikeDAO implements DAO<Like> {
    @Override
    public Optional<Like> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Like> getAllBy(Predicate<Like> p) {
        return null;
    }

    @Override
    public Collection<Like> getAll() {
        return null;
    }

    @Override
    public void update(Like like) {

    }

    @Override
    public void insert(Like like) {

    }

    @Override
    public void delete(Like like) {

    }
}
