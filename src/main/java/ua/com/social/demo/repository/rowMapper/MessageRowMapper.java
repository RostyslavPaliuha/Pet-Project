package ua.com.social.demo.repository.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.social.demo.entity.impl.Message;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageRowMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();
        message.setMessageId(rs.getInt("message_id"));
        message.setMessageContext(rs.getString("message_content"));
        message.setMessageDate(rs.getTimestamp("message_date"));
        message.setConversationId(rs.getInt("conversation_id"));
        return message;
    }
}
