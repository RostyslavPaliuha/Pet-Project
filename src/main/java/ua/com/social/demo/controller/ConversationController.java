package ua.com.social.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.social.demo.entity.impl.Conversation;
import ua.com.social.demo.entity.impl.Message;
import ua.com.social.demo.service.api.ConversationService;
import ua.com.social.demo.service.api.MessageService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/profile")
public class ConversationController {
    private static final Logger LOG = Logger.getLogger(ConversationController.class);
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private MessageService messageService;

    @PostMapping("/{profileId}/send-msg-to/{companionId}")
    public ResponseEntity postMessage(@PathVariable("profileId") Integer profileId, @PathVariable("companionId") Integer companionId, @RequestBody Message message) {
        try {
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
            return new ResponseEntity("Message send successful!", HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Message send from profile " + profileId + " to profile " + companionId + " failed." + e.getMessage());
            return new ResponseEntity("Unsuccessful message send!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{profileId}/review-conversations")
    public ResponseEntity reviewConversations(@PathVariable("profileId") Integer profileId) {
        try {
            List<Conversation> conversations = conversationService.reviewConversations(profileId);
            return new ResponseEntity(conversations, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Review profile " + profileId + " conversations failed." + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt to reviewe conversations.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{profileId}/review-conversation/{conversationId}")
    public ResponseEntity reviewConversation(@PathVariable("profileId") Integer profileId, @PathVariable("conversationId") Integer conversationId) {
        try {
            List<Message> conversation = messageService.getAllByConversation(conversationId);
            return new ResponseEntity(conversation, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Review profile " + profileId + " conversation failed." + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt to review certain conversation.", HttpStatus.BAD_REQUEST);
        }
    }

}
