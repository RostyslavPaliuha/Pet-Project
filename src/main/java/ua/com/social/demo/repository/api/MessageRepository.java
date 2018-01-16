package ua.com.social.demo.repository.api;

import ua.com.social.demo.entity.impl.Message;

import java.util.List;

public interface MessageRepository extends EntityRepository<Message> {

    List<Message> getAllByConversation(Integer conversationId) throws Exception;
}
