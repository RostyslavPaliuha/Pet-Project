package ua.com.social.demo.repository.api;

import ua.com.social.demo.entity.impl.Conversation;

import java.util.List;

public interface ConversationRepository extends EntityRepository<Conversation> {

    List<Conversation> readAll(Integer profileId);

    Conversation getByProfileIdCompanionId(Integer profileId, Integer companionId) throws Exception;
}
