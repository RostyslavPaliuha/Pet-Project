package ua.com.social.demo.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Profile;
import ua.com.social.demo.repository.ProfileRepository;
import ua.com.social.demo.repository.rowMapper.ProfileRowMapper;

import java.sql.PreparedStatement;

@Repository("profileRepository")
public class ProfileRepositoryImpl implements ProfileRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Integer persistAndRetrieveId(Profile profile) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO profile(account_id) VALUES (?);", new String[]{"profile_id"});
                    ps.setInt(1, profile.getAccountId());
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Profile get(Integer accountId) throws EmptyResultDataAccessException {
        return jdbcOperations.queryForObject("SELECT * FROM profile WHERE account_id= ?", new Object[]{accountId}, new ProfileRowMapper());
    }

    @Override
    public void changeOnlineStatus(Integer profileId, Integer onlineStatus) {
        jdbcOperations.update("UPDATE profile SET online_status=? WHERE profile_id=?", onlineStatus, profileId);
    }
}
