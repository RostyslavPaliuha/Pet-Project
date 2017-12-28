package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Message;

import java.util.List;

public interface MessageService {
    public boolean persist(Message message);

    public boolean delete(Integer id);

    public Message get(Integer id);

    public List<Message> getAllByConversation(Integer conversationId);

}
