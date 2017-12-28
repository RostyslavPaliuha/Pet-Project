package ua.com.social.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Conversation;
import ua.com.social.demo.repository.impl.ConversationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationServiceImpl {
    @Autowired
    private ConversationRepository conversationRepository;

    public Integer persist(Conversation conversation) {
        return conversationRepository.persistAndRetrieveId(conversation);
    }

    public List<Conversation> reviewConversations(Integer profileId) {

        return conversationRepository.getAll(profileId);
    }

    public Conversation getConversation(Integer profileId, Integer companionId) {
        try {
            return conversationRepository.getByProfileIdCompanionId(profileId, companionId);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
