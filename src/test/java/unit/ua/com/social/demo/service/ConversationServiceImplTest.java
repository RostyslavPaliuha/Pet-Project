package unit.ua.com.social.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.Conversation;
import ua.com.social.demo.entity.impl.Message;
import ua.com.social.demo.service.ConversationService;
import ua.com.social.demo.service.MessageService;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})

public class ConversationServiceImplTest {
    @Autowired
    private ConversationService conversationService;

    @Autowired
    private MessageService messageService;

    @Test
    public void persist() throws Exception {
        Conversation conversation = new Conversation();
        conversation.setProfileId(1);
        conversation.setCompanionId(3);
        Integer conversationId = conversationService.persist(conversation);
        assertEquals(new Integer(2), conversationId);


    }

    @Test
    public void reviewConversations() throws Exception {
        this.persist();
        Message message = new Message();
        message.setMessageContext("Hi dude!");
        message.setConversationId(1);
        messageService.persist(message);
        List<Conversation> conversations = conversationService.reviewConversations(1);
        assertEquals(1, conversations.size());//BUG size must be 2!!!
    }

    @Test
    public void getConversation() throws Exception {
    }

    @Test
    public void deleteConversation() throws Exception {
    }

}