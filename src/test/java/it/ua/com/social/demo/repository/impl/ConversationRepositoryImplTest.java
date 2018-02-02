package it.ua.com.social.demo.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.*;
import ua.com.social.demo.repository.api.AccountRepository;
import ua.com.social.demo.repository.api.ConversationRepository;
import ua.com.social.demo.repository.api.ProfileDetailsRepository;
import ua.com.social.demo.repository.api.ProfileRepository;
import ua.com.social.demo.service.api.MessageService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})

public class ConversationRepositoryImplTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileDetailsRepository detailsRepository;
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private MessageService messageService;
    private Account account;
    private Profile profile;
    private ProfileDetails profileDetails;
    private Conversation firstConversation;
    private Conversation secondConversation;

    public ConversationRepositoryImplTest() {
        this.account = new Account("testAccount@gmail.com", "$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni");
        this.profile = new Profile();
        this.profileDetails = new ProfileDetails("testName", "testLastNAme", ProfileDetails.Sex.male, LocalDate.of(1992, 03, 16));
        this.firstConversation = new Conversation();
        this.secondConversation = new Conversation();

    }

    private void createMessageForConversation(Integer conversationId) {
        Message message = new Message();
        message.setMessageContext("Hi dude!");
        message.setConversationId(conversationId);
        messageService.persist(message);
    }

    @Test
    public void createConversation_reviewConversations() throws Exception {
        firstConversation.setProfileId(1);
        firstConversation.setCompanionId(3);
        secondConversation.setProfileId(2);
        secondConversation.setCompanionId(3);
        Integer conversationId = conversationRepository.create(firstConversation);
        Integer secondConversationId = conversationRepository.create(secondConversation);
        createMessageForConversation(conversationId);
        createMessageForConversation(secondConversationId);
        assertEquals(new Integer(2), conversationId);
        assertEquals(new Integer(3), secondConversationId);
        Conversation certainConversation = conversationRepository.getByProfileIdCompanionId(1, 3);
        Conversation certainSecondConversation = conversationRepository.getByProfileIdCompanionId(2, 3);
        assertEquals(firstConversation.getProfileId(), certainConversation.getProfileId());
        assertEquals(firstConversation.getCompanionId(), certainConversation.getCompanionId());
        assertEquals(secondConversation.getProfileId(), certainSecondConversation.getProfileId());
        assertEquals(secondConversation.getCompanionId(), certainSecondConversation.getCompanionId());
        List<Conversation> conversations = conversationRepository.readAll(1);
        assertEquals(2, conversations.size());
    }

}
