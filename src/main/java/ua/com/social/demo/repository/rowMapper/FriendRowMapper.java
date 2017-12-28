package ua.com.social.demo.repository.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.social.demo.entity.impl.Friend;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Friend friend = new Friend();
        friend.setFirstName(rs.getString("first_name"));
        friend.setLastName(rs.getString("last_name"));
        friend.setFriendProfileId(rs.getInt("profile_id"));
        return friend;
    }


}
