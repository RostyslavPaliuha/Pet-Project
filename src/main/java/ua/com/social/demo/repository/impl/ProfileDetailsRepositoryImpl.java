package ua.com.social.demo.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.repository.api.AbstractRepository;
import ua.com.social.demo.repository.api.ProfileDetailsRepository;
import ua.com.social.demo.repository.rowMapper.ProfileDetailsRowMapper;

import java.sql.Date;
import java.sql.PreparedStatement;

@Repository("profileDetailsRepository")
public class ProfileDetailsRepositoryImpl extends AbstractRepository<ProfileDetails> implements ProfileDetailsRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Integer create(ProfileDetails profileDetails) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO profile_details(first_name, last_name, sex, birthday,profile_id) VALUES (?,?,?,?,?);", new String[]{"profile_details_id"});
                    ps.setString(1, profileDetails.getFirstName());
                    ps.setString(2, profileDetails.getLastName());
                    ps.setString(3, profileDetails.getSex());
                    ps.setDate(4, Date.valueOf(profileDetails.getBirthDay()));
                    ps.setInt(5, profileDetails.getProfileId());
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public ProfileDetails read(Integer profileId) throws EmptyResultDataAccessException {
        return jdbcOperations.queryForObject("SELECT * FROM profile_details pd WHERE pd.profile_id=?", new Integer[]{profileId}, new ProfileDetailsRowMapper());
    }

    @Override
    public void update(ProfileDetails profileDetails) {
        jdbcOperations.update("UPDATE  profile_details SET first_name=?,last_name=?,sex=?,birthday=? WHERE profile_id=?", profileDetails.getFirstName(), profileDetails.getLastName(), profileDetails.getSex(), profileDetails.getBirthDay(), profileDetails.getProfileId());
    }

    @Override
    public void delete(Integer profileId) {
        jdbcOperations.update("DELETE FROM profile_details WHERE profile_details_id =?", profileId);
    }


}
