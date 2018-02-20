package it.ua.com.social.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.Conversation;
import ua.com.social.demo.entity.impl.Message;
import ua.com.social.demo.service.api.ConversationService;
import ua.com.social.demo.service.api.MessageService;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ConversationControllerTest  extends LoginControllerTest{
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private Filter springSecurityFilterChain;
    @MockBean
    private ConversationService conversationService;
    @MockBean
    private MessageService messageService;
    private List<Message> messageList = Arrays.asList(new Message("TEST MESSAGE"));
    private List<Conversation> conversationList = Arrays.asList(new Conversation(messageList));

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
        Mockito.when(conversationService.getConversation(1, 2)).thenReturn(null);
        Mockito.when(conversationService.reviewConversations(1)).thenReturn(conversationList);
        Mockito.when(messageService.getAllByConversation(2)).thenReturn(messageList);

    }

    @Test
    public void login_postMessage_reviewConversations_reviewConversation() throws Exception {
      login();
      String token=super.getHeader();
        mockMvc.perform(post("/api/profile/1/send-msg-to/2")
                .header("Authentication", token)
                .content("{\"messageContent\":\"TEST MESSAGE\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andDo(print());
        mockMvc.perform(get("/api/profile/1/review-conversations")
                .header("Authentication", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$..messageContext").value("TEST MESSAGE"));
        mockMvc.perform(get("/api/profile/1/review-conversation/2")
                .header("Authentication", token))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }


}