package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Conversation;
import ua.com.social.demo.repository.impl.ConversationRepository;
import ua.com.social.demo.service.ConversationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("conversationService")
public class ConversationServiceImpl implements ConversationService {
    @Autowired
    private ConversationRepository conversationRepository;
    private static final Logger LOG = Logger.getLogger(ConversationServiceImpl.class);

    public Integer persist(Conversation conversation) {
        try {
            return conversationRepository.persistAndRetrieveId(conversation);
        } catch (Exception e) {
            LOG.error("Error while saving conversation" + e.getMessage(), e);
            return null;
        }
    }

    public List<Conversation> reviewConversations(Integer profileId) {
        try {
            return conversationRepository.getAll(profileId);

        } catch (Exception e) {
            LOG.error("Error while review conversations" + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Conversation getConversation(Integer profileId, Integer companionId) {
        try {
            return conversationRepository.getByProfileIdCompanionId(profileId, companionId);
        } catch (Exception e) {
            LOG.error("Error while saving conversation" + e.getMessage(), e);
            return null;
        }
    }
}
