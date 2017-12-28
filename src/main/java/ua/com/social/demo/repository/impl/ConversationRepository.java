package ua.com.social.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Conversation;
import ua.com.social.demo.repository.EntityRepository;
import ua.com.social.demo.repository.ExtendedEntityRepository;
import ua.com.social.demo.repository.rowMapper.ConversationRowMapper;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;

@Repository
public class ConversationRepository implements EntityRepository<Conversation>, ExtendedEntityRepository<Conversation> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public void persist(Conversation conversation) {
        Object[] params = new Object[]{conversation.getProfileId()};
        int[] types = new int[]{Types.INTEGER, Types.INTEGER};
        jdbcOperations.update("INSERT INTO social.conversation (companion_id, profile_id) VALUES (?, ?)", params, types);
    }

    @Override
    public void delete(Conversation conversation) {
        jdbcOperations.update("DELETE FROM conversation WHERE conversation_id =?", new Object[]{conversation.getProfileId()});
    }

    @Override
    public Conversation get(Integer id) {
        return jdbcOperations.queryForObject("SELECT * FROM conversation WHERE conversation_id= ?", new Object[]{id}, new ConversationRowMapper());
    }


    @Override
    public Integer persistAndRetrieveId(Conversation conversation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO social.conversation( profile_id,companion_id) VALUES (?,?);", new String[]{"conversation_id"});
                    ps.setInt(1, conversation.getProfileId());
                    ps.setInt(2, conversation.getCompanionId());
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }


    @Override
    public List<Conversation> getAll(Integer profileId) {
        return jdbcOperations.query("SELECT * FROM conversation WHERE profile_id= ? And companion_id=?", new Object[]{profileId,profileId}, new ConversationRowMapper());

    }

    public Conversation getByProfileIdCompanionId(Integer profileId, Integer companionId) throws Exception {

       Conversation conversation=jdbcOperations.queryForObject("SELECT * FROM conversation WHERE profile_id= ? AND companion_id=?;", new Object[]{profileId, companionId}, new ConversationRowMapper());
        return conversation;
    }
}
