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
import ua.com.social.demo.repository.api.*;
import ua.com.social.demo.service.impl.RegistrationServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})

public class MessageRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileDetailsRepository detailsRepository;
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private MessageRepository messageRepository;
    private Account account;
    private Profile profile;
    private ProfileDetails profileDetails;
    private Conversation firstConversation;
    private Message message;

    public MessageRepositoryTest() {
        this.account = new Account("testAccount@gmail.com", "$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni", RegistrationServiceImpl.prepareActivateLink());
        this.profile = new Profile();
        this.profileDetails = new ProfileDetails("testName", "testLastNAme", ProfileDetails.Sex.male, LocalDate.of(1992, 03, 16));
        this.firstConversation = new Conversation();

    }

    @Test
    public void createAcc_createConversation_createMessage_delete() throws Exception {
        message = new Message();
        message.setConversationId(1);
        message.setMessageDate(Timestamp.valueOf(LocalDateTime.now()));
        message.setMessageContext("TEST MESSAGE!");
        messageRepository.create(message);
        List<Message> messages = messageRepository.getAllByConversation(1);
        assertEquals(2, messages.size());
        assertEquals(message.getMessageContext(), messages.get(1).getMessageContext());
        message.setMessageId(messages.get(0).getMessageId());
        messageRepository.delete(message.getMessageId());
        List<Message> afterDeletemessages = messageRepository.getAllByConversation(1);
        assertEquals(1, afterDeletemessages.size());
    }
}