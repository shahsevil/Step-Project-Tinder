package org.tinder_proj.service;

import lombok.extern.log4j.Log4j2;
import org.tinder_proj.dao.DAOMessage;
import org.tinder_proj.dao.DAOUser;
import org.tinder_proj.entity.Message;
import org.tinder_proj.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Log4j2
public class MessageService {
    private final DAOUser DAO_USER;
    private final DAOMessage DAO_MESSAGE;

    public MessageService(DAOUser DAO_USER, DAOMessage DAO_MESSAGE) {
        this.DAO_USER = DAO_USER;
        this.DAO_MESSAGE = DAO_MESSAGE;
    }

    public List<Message> getMessages(int who_id, int whom_id) {
        String SQL = String.format("SELECT * FROM messages WHERE from_id = %d AND to_id = %d OR from_id = %d AND to_id = %d ORDER BY date",
                who_id, whom_id, whom_id, who_id);
        return DAO_MESSAGE.getBy(SQL);
    }


    public User getUserInfo(int id) {
        Optional<User> user = DAO_USER.get(id);
        if (user.isPresent()) return user.get();
        else {
            log.error("Something went wrong while getting user from db!!!");
            return null;
        }
    }

    public void addMessage(int who_id, int whom_id, String content, LocalDate date) {
        DAO_MESSAGE.insert(new Message(who_id, whom_id, content, date));
    }
}
