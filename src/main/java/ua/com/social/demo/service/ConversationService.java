package ua.com.social.demo.service;


import ua.com.social.demo.entity.impl.Conversation;

import java.util.List;

public interface ConversationService {
    public Integer persist(Conversation conversation);

    public List<Conversation> reviewConversations(Integer id);

    public Conversation getConversation(Integer profileId, Integer companionId);

    boolean deleteConversation(Integer conversationId);
}
