package ua.com.social.demo.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.social.demo.entity.impl.ProfileDetails;

public interface ProfileDetailsRepository {

    void delete(Integer profileId);

    ProfileDetails get(Integer profileId) throws EmptyResultDataAccessException;

    Integer persistAndRetrieveId(ProfileDetails profileDetails);

    void update(ProfileDetails profileDetails);

}
