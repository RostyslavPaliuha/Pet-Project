package ua.com.social.demo.repository.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.social.demo.entity.impl.Conversation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ConversationRowMapper implements RowMapper<Conversation> {
    @Override
    public Conversation mapRow(ResultSet rs, int rowNum) throws SQLException {
        int size = rs.getMetaData().getColumnCount();
        if (size == 3) {
            Conversation conversation = new Conversation();
            conversation.setConversationId(rs.getInt("conversation_id"));
            conversation.setCompanionId(rs.getInt("companion_id"));
            conversation.setProfileId(rs.getInt("profile_id"));
            return conversation;
        } else if (size == 9) {
            Conversation fullConversation = new Conversation();
            fullConversation.setConversationId(rs.getInt("conversation_id"));
            fullConversation.setCompanionId(rs.getInt("companion_id"));
            fullConversation.setProfileId(rs.getInt("profile_id"));
            fullConversation.setProfileName(rs.getString("profile_name"));
            fullConversation.setProfileLastName(rs.getString("profile_lastname"));
            fullConversation.setCompanionName(rs.getString("companion_name"));
            fullConversation.setCompanionName(rs.getString("companion_lastname"));
            fullConversation.setLastMsgDate(rs.getTimestamp("date"));
            fullConversation.setLastMessageContent(rs.getString("message"));
            return fullConversation;
        }
        return null;
    }
}
