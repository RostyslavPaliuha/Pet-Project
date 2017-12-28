package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Message;
import ua.com.social.demo.repository.impl.MessageRepository;
import ua.com.social.demo.service.MessageService;

import java.util.List;

@Service
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
            LOG.error("Error while saving data" + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Message get(Integer id) {
        return null;
    }

    public List<Message> getAllByConversation(Integer conversationId) {
        return messageRepository.getAllByConversation(conversationId);
    }
}
