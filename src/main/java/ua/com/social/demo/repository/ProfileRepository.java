package ua.com.social.demo.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.social.demo.entity.impl.Profile;

public interface ProfileRepository {

    Integer persistAndRetrieveId(Profile profile);

    Profile get(Integer id) throws EmptyResultDataAccessException;

    void changeOnlineStatus(Integer profileId, Integer onlineStatus);
}
