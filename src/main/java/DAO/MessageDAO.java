package DAO;

import entities.Message;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public class MessageDAO implements DAO<Message> {
    @Override
    public Optional<Message> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Message> getAllBy(Predicate<Message> p) {
        return null;
    }

    @Override
    public Collection<Message> getAll() {
        return null;
    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void insert(Message message) {

    }

    @Override
    public void delete(Message message) {

    }
}
