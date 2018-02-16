package ua.com.social.demo.repository.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.social.demo.entity.impl.ProfileDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDetailsRowMapper implements RowMapper<ProfileDetails> {

    @Override
    public ProfileDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProfileDetails profileDetails = new ProfileDetails();
        profileDetails.setProfileDetailsId(rs.getInt("profile_details_id"));
        profileDetails.setProfileId(rs.getInt("profile_id"));
        profileDetails.setFirstName(rs.getString("first_name"));
        profileDetails.setLastName(rs.getString("last_name"));
        profileDetails.setSex(rs.getString("sex"));
        profileDetails.setBirthDay((rs.getDate("birthday")).toLocalDate());
        profileDetails.setCountry((rs.getString("country")));
        //profileDetails.setAvatar(rs.getString("avatar"));???
        return profileDetails;
    }
}
