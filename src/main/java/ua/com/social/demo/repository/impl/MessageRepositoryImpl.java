package ua.com.social.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Message;
import ua.com.social.demo.repository.api.AbstractRepository;
import ua.com.social.demo.repository.api.MessageRepository;
import ua.com.social.demo.repository.rowMapper.MessageRowMapper;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Repository("messageRepository")
public class MessageRepositoryImpl extends AbstractRepository<Message> implements MessageRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Integer create(Message message) {
        Object[] params = new Object[]{message.getMessageContext(), message.getMessageDate(), message.getConversationId()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO message(message_content, message_date, conversation_id) VALUES (?,?,?);", new String[]{"message_id"});
                    ps.setString(1, (String) params[0]);
                    ps.setTimestamp(2, (Timestamp) params[1]);
                    ps.setInt(3, (int) params[2]);
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void delete(Integer messageId) {
        jdbcOperations.update("DELETE FROM message WHERE message_id =?", messageId);
    }

    @Override
    public Message read(Integer messageId) {
        return jdbcOperations.queryForObject("SELECT * FROM message WHERE message_id= ?", new Object[]{messageId}, new MessageRowMapper());
    }

    @Override
    public List<Message> getAllByConversation(Integer conversationId) {
        return jdbcOperations.query("SELECT * FROM message WHERE conversation_id= ?", new Integer[]{conversationId}, new MessageRowMapper());
    }

}
