package ua.com.social.demo.service;


import ua.com.social.demo.entity.impl.Conversation;

public interface ConversationService {
    public boolean persist(Conversation conversation);

    public boolean delete(Integer id);

    public Conversation get(Integer id);

}
