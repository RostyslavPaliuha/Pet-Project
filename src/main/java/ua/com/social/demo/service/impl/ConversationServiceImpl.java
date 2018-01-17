package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Conversation;
import ua.com.social.demo.repository.api.ConversationRepository;
import ua.com.social.demo.service.api.ConversationService;

import java.util.Collections;
import java.util.List;

@Service("conversationService")
public class ConversationServiceImpl implements ConversationService {
    private static final Logger LOG = Logger.getLogger(ConversationServiceImpl.class);
    @Autowired
    private ConversationRepository conversationRepository;

    public Integer persist(Conversation conversation) {
        try {
            return conversationRepository.create(conversation);
        } catch (Exception e) {
            LOG.error("Error while saving conversation" + e.getMessage(), e);
            return null;
        }
    }

    public List<Conversation> reviewConversations(Integer profileId) {
        try {
            return conversationRepository.readAll(profileId);
        } catch (Exception e) {
            LOG.error("Error while review conversations" + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Conversation getConversation(Integer profileId, Integer companionId) {
        try {
            return conversationRepository.getByProfileIdCompanionId(profileId, companionId);
        } catch (Exception e) {
            LOG.error("Error while getting conversation" + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public boolean deleteConversation(Integer conversationId) {
        try {
            conversationRepository.delete(conversationId);
            return true;
        } catch (Exception e) {
            LOG.error("Error while deleting conversation" + e.getMessage(), e);
            return false;
        }
    }
}
