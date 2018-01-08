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
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO conversation( profile_id,companion_id) VALUES (?,?);", new String[]{"conversation_id"});
                    ps.setInt(1, conversation.getProfileId());
                    ps.setInt(2, conversation.getCompanionId());
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }


    @Override
    public List<Conversation> getAll(Integer profileId) {
       String sql= "SELECT c.conversation_id, c.profile_id, c.companion_id, pd.first_name  AS profile_lastname, pd.last_name   AS profile_name, pd2.first_name AS companion_name, pd2.last_name  AS companion_lastname,  m.message_content AS message, m.message_date AS date FROM conversation c JOIN profile_details pd ON pd.profile_id = c.profile_id JOIN profile_details pd2 ON pd2.profile_id = c.companion_id JOIN message m ON m.conversation_id=c.conversation_id WHERE c.profile_id = ? OR c.companion_id=? ORDER BY m.message_id DESC LIMIT 1";
       /* return jdbcOperations.query("SELECT\n" +
                "  c.conversation_id,\n" +
                "  c.profile_id,\n" +
                "  c.companion_id,\n" +
                "  pd.first_name  AS profile_lastname,\n" +
                "  pd.last_name   AS profile_name,\n" +
                "  pd2.first_name AS companion_name,\n" +
                "  pd2.last_name  AS companion_lastname,\n" +
                "  m.message_content AS message,\n" +
                "  m.message_date AS date\n" +
                "FROM conversation c\n" +
                "  JOIN profile_details pd ON\n" +
                "                            pd.profile_id = c.profile_id\n" +
                "  JOIN profile_details pd2 ON pd2.profile_id = c.companion_id\n" +
                "  JOIN message m ON m.conversation_id=c.conversation_id\n" +
                "WHERE c.profile_id = ? OR c.companion_id=?\n" +
                "ORDER BY m.message_id DESC LIMIT 1", new Object[]{profileId,profileId}, new ConversationRowMapper());*/

        return jdbcOperations.query(sql,new Object[]{profileId,profileId}, new ConversationRowMapper());
    }

    public Conversation getByProfileIdCompanionId(Integer profileId, Integer companionId) throws Exception {

       Conversation conversation=jdbcOperations.queryForObject("SELECT * FROM conversation WHERE profile_id= ? AND companion_id=?;", new Object[]{profileId, companionId}, new ConversationRowMapper());
        return conversation;
    }
}
