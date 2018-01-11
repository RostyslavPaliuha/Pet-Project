package ua.com.social.demo.repository;

import ua.com.social.demo.entity.impl.Message;

import java.util.List;

public interface MessageRepository {
    void persist(Message message);

    void delete(Integer messageId);

    Message get(Integer id);

    List<Message> getAllByConversation(Integer conversationId);
}
