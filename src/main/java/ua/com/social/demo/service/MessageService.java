package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    public boolean persist(Message message);

    public boolean delete(Integer messageId);

    public Optional<Message> get(Integer messageId);

    public List<Message> getAllByConversation(Integer conversationId);

}
