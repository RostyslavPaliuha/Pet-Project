package ua.com.social.demo.repository;

import ua.com.social.demo.entity.impl.Conversation;

import java.util.List;

public interface ConversationRepository {

    void delete(Integer conversationId);

    Conversation get(Integer id);

    Integer persistAndRetrieveId(Conversation conversation);

    List<Conversation> getAll(Integer profileId);

    Conversation getByProfileIdCompanionId(Integer profileId, Integer companionId) throws Exception;
}
