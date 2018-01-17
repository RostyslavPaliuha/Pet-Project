package unit.ua.com.social.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.Message;
import ua.com.social.demo.service.api.MessageService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})
public class MessageSeviceImplTest {
    @Autowired
    private MessageService messageService;
    private Message message;

    @Before
    public void setUp() {
        message = new Message();
        message.setConversationId(1);
        message.setMessageContext("Greetings!");
        message.setMessageDate(Timestamp.valueOf(LocalDateTime.now()));
    }

    @Test
    public void persist() throws Exception {
        Boolean confirmation = messageService.persist(message);
        assertTrue(confirmation);
    }

    @Test
    public void delete() throws Exception {
        Boolean confirmation = messageService.delete(3);
        assertTrue(confirmation);
    }

    @Test
    public void get() throws Exception {
        this.persist();
        Optional<Message> actualMessage = messageService.get(2);
        assertTrue(actualMessage.isPresent());
        assertEquals(message.getMessageContext(), actualMessage.get().getMessageContext());
    }

    @Test
    public void getAllByConversation() throws Exception {
        this.messageService.persist(message);
        List<Message> actualMessageList = messageService.getAllByConversation(1);
        assertEquals(new Integer(2), new Integer(actualMessageList.size()));
    }

}