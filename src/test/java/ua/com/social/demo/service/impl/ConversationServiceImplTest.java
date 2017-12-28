package ua.com.social.demo.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.social.demo.entity.impl.Conversation;
import ua.com.social.demo.repository.impl.ConversationRepository;
import ua.com.social.demo.service.ConversationService;

import static org.junit.Assert.*;

public class ConversationServiceImplTest {
    @Autowired
    private ConversationServiceImpl conversationService;
    @Autowired
    private ConversationRepository conversationRepository;
    @Test
    public void getConversation() throws Exception {
        Conversation conversation = conversationRepository.getByProfileIdCompanionId(1,2);
        assertNull(conversation);
    }

}