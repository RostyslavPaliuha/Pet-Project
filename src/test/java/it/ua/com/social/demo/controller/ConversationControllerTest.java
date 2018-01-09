package it.ua.com.social.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.social.demo.DemoApplication;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ConversationControllerTest {
    private MockMvc mockMvc;

    @Test
    public void postMessage() throws Exception {
    }

    @Test
    public void reviewConversations() throws Exception {
    }

    @Test
    public void reviewConversation() throws Exception {
    }

}