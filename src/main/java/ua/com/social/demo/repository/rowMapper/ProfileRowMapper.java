package ua.com.social.demo.repository.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.social.demo.entity.impl.Profile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileRowMapper implements RowMapper<Profile> {
    @Override
    public Profile mapRow(ResultSet rs, int row) throws SQLException {
        Profile profile = new Profile();
        profile.setProfileId(rs.getInt("profile_id"));
        profile.setAccountId(rs.getInt("account_id"));
        profile.setOnlineStatus(rs.getInt("online_status"));
        return profile;
    }
}
