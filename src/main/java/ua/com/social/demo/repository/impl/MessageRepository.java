package ua.com.social.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Message;
import ua.com.social.demo.repository.EntityRepository;
import ua.com.social.demo.repository.rowMapper.MessageRowMapper;

import java.sql.Types;
import java.util.List;

@Repository
public class MessageRepository implements EntityRepository<Message> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public void persist(Message message) {
        Object[] params = new Object[]{message.getMessageContext(),message.getMessageDate(),message.getConversationId() };
        int[] types = new int[]{Types.VARCHAR,Types.TIMESTAMP,Types.INTEGER };
        jdbcOperations.update("INSERT INTO message(message_content, message_date, conversation_id) VALUES (?,?,?);", params, types);
    }

    @Override
    public void delete(Message message) {
        jdbcOperations.update("DELETE FROM message WHERE message_id =" + message.getMessageId() + ";");
    }

    @Override
    public Message get(Integer id) {
        return jdbcOperations.queryForObject("SELECT * FROM message where message_id= ?", new Object[]{id}, new MessageRowMapper());
    }
    public List<Message> getAllByConversation(Integer conversationId){
        return jdbcOperations.query("SELECT * FROM message where conversation_id= ?", new Object[]{conversationId}, new MessageRowMapper());
    }

}
