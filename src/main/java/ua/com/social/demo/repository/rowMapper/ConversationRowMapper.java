package ua.com.social.demo.repository.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.social.demo.entity.impl.Conversation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConversationRowMapper implements RowMapper<Conversation> {
    @Override
    public Conversation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Conversation conversation = new Conversation();
        conversation.setConversationId(rs.getInt("conversation_id"));
        conversation.setCompanionId(rs.getInt("companion_id"));
        conversation.setProfileId(rs.getInt("profile_id"));

        return conversation;
    }
}
