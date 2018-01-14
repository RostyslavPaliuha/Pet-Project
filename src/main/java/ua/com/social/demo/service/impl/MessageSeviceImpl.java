package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Message;
import ua.com.social.demo.repository.MessageRepository;
import ua.com.social.demo.service.MessageService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("messageService")
public class MessageSeviceImpl implements MessageService {
    private static final Logger LOG = Logger.getLogger(AccountServiceImpl.class);

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public boolean persist(Message message) {
        try {
            messageRepository.persist(message);
            return true;
        } catch (Exception e) {
            LOG.error("Error while saving message" + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean delete(Integer messageId) {
        try {
            messageRepository.delete(messageId);
            return true;
        } catch (Exception e) {
            LOG.error("Error while deleting message" + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Optional<Message> get(Integer id) {
        try {
            return Optional.ofNullable(messageRepository.get(id));
        } catch (Exception e) {
            LOG.error("Error while getting message" + e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public List<Message> getAllByConversation(Integer conversationId) {
        try {
            return messageRepository.getAllByConversation(conversationId);
        } catch (Exception e) {
            LOG.error("Error while getting certain conversation messages" + e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
