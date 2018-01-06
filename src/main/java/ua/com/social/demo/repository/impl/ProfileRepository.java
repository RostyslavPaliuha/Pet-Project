package ua.com.social.demo.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Profile;
import ua.com.social.demo.repository.EntityRepository;
import ua.com.social.demo.repository.ExtendedEntityRepository;
import ua.com.social.demo.repository.rowMapper.ProfileRowMapper;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;

@Repository("profileRepository")
public class ProfileRepository implements EntityRepository<Profile>, ExtendedEntityRepository<Profile> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public void persist(Profile profile) {
        Object[] params = new Object[]{profile.getAccountId()};
        int[] types = new int[]{Types.INTEGER};
        jdbcOperations.update("INSERT INTO profile(account_id) VALUES (?);", params, types);
    }

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
    public List<Profile> getAll(Integer id) {
        return null;
    }

    @Override
    public void delete(Profile profile) {
        jdbcOperations.update("DELETE FROM profile WHERE account_id =" + profile.getAccountId() + ";");
    }

    @Override
    public Profile get(Integer id) throws EmptyResultDataAccessException {
        return jdbcOperations.queryForObject("SELECT * FROM profile where account_id= ?", new Object[]{id}, new ProfileRowMapper());
    }

    public void changeOnlineStatus(Integer profileId, Integer onlineStatus) {
        jdbcOperations.update("UPDATE profile SET online_status=? WHERE profile_id=?", onlineStatus, profileId);
    }
}
