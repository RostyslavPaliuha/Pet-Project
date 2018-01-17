package ua.com.social.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.social.demo.entity.impl.Conversation;
import ua.com.social.demo.entity.impl.Message;
import ua.com.social.demo.service.api.ConversationService;
import ua.com.social.demo.service.api.MessageService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ConversationController {

    @Autowired
    private ConversationService conversationService;
    @Autowired
    private MessageService messageService;

    @PostMapping("api/profile/{profileId}/send-msg-to/{companionId}")
    @ResponseStatus(HttpStatus.OK)
    public void postMessage(@PathVariable("profileId") Integer profileId, @PathVariable("companionId") Integer companionId, @RequestBody Message message) {
        Integer conversationId;
        Conversation conversation = conversationService.getConversation(profileId, companionId);
        if (conversation == null) {
            Conversation conversation1 = new Conversation();
            conversation1.setProfileId(profileId);
            conversation1.setCompanionId(companionId);
            conversationId = conversationService.persist(conversation1);
            message.setMessageDate(Timestamp.valueOf(LocalDateTime.now()));
            message.setConversationId(conversationId);
            messageService.persist(message);
        } else {
            conversationId = conversation.getConversationId();
            message.setMessageDate(Timestamp.valueOf(LocalDateTime.now()));
            message.setConversationId(conversationId);
            messageService.persist(message);
        }
    }

    @GetMapping("api/profile/{id}/review-conversations")
    @ResponseStatus(HttpStatus.OK)
    public List<Conversation> reviewConversations(@PathVariable("id") Integer profileId) {
        return conversationService.reviewConversations(profileId);
    }

    @GetMapping("api/profile/{id}/review-conversation/{conversationId}")
    public List<Message> reviewConversation(@PathVariable("id") Integer profileId, @PathVariable("conversationId") Integer conversationId) {
        return messageService.getAllByConversation(conversationId);
    }

}
