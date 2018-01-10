package ua.com.social.demo.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.repository.EntityRepository;
import ua.com.social.demo.repository.ExtendedEntityRepository;
import ua.com.social.demo.repository.rowMapper.ProfileDetailsRowMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;

@Repository("profileDetailsRepository")
public class ProfileDetailsRepository implements EntityRepository<ProfileDetails>, ExtendedEntityRepository<ProfileDetails> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public void persist(ProfileDetails profileDetails) {
        Object[] params = new Object[]{profileDetails.getProfileDetailsId(), profileDetails.getFirstName(), profileDetails.getLastName(), profileDetails.getSex(), profileDetails.getBirthDay(), profileDetails.getProfileId()};
        int[] types = new int[]{Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.DATE,Types.INTEGER};
        jdbcOperations.update("INSERT INTO profile_details(first_name, last_name, sex, birthday,profile_id) VALUES (?,?,?,?);", params, types);
    }

    @Override
    public void delete(ProfileDetails profileDetails) {
        jdbcOperations.update("DELETE FROM profile_details WHERE profile_details_id =?",new Object[]{profileDetails.getProfileDetailsId()});
    }

    @Override
    public ProfileDetails get(Integer id) throws EmptyResultDataAccessException {
        return jdbcOperations.queryForObject("SELECT * FROM profile_details where profile_id= ?", new Object[]{id}, new ProfileDetailsRowMapper());
    }

    @Override
    public Integer persistAndRetrieveId(ProfileDetails profileDetails) {
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
    public List<ProfileDetails> getAll(Integer id) {
        return null;
    }

    public void update(ProfileDetails profileDetails){
        jdbcOperations.update("UPDATE  profile_details SET first_name=?,last_name=?,sex=?,birthday=? WHERE profile_id=?",profileDetails.getFirstName(),profileDetails.getLastName(),profileDetails.getSex(),profileDetails.getBirthDay(),profileDetails.getProfileId());
    }
    public void updateFirstName(String firstName,Integer profileId){

    }
    public void updateLastName(String lastName, Integer profileId){

    }
    public void updateSex(String sex,Integer profileId){

    }
    public void updateBirthdayDate(){

    }
}
