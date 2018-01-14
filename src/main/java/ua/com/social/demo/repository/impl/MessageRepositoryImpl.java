package ua.com.social.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Message;
import ua.com.social.demo.repository.MessageRepository;
import ua.com.social.demo.repository.rowMapper.MessageRowMapper;

import java.util.List;

@Repository("messageRepository")
public class MessageRepositoryImpl implements MessageRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public void persist(Message message) {
        Object[] params = new Object[]{message.getMessageContext(), message.getMessageDate(), message.getConversationId()};
        jdbcOperations.update("INSERT INTO message(message_content, message_date, conversation_id) VALUES (?,?,?);", params);
    }

    @Override
    public void delete(Integer messageId) {
        jdbcOperations.update("DELETE FROM message WHERE message_id =?", messageId);
    }

    @Override
    public Message get(Integer messageId) {
        return jdbcOperations.queryForObject("SELECT * FROM message WHERE message_id= ?", new Object[]{messageId}, new MessageRowMapper());
    }

    @Override
    public List<Message> getAllByConversation(Integer conversationId) {
        return jdbcOperations.query("SELECT * FROM message WHERE conversation_id= ?", new Integer[]{conversationId}, new MessageRowMapper());
    }

}
