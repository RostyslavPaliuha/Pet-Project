package ua.com.social.demo.repository.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.social.demo.entity.impl.FriendList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendListRowMapper implements RowMapper<FriendList> {
    @Override
    public FriendList mapRow(ResultSet rs, int rowNum) throws SQLException {
        FriendList friendList = new FriendList();
        friendList.setId(rs.getInt("id"));
        friendList.setProfileId(rs.getInt("profile_id"));
        friendList.setFriendProfileId(rs.getInt("friend_profile_id"));
        return friendList;
    }


}
