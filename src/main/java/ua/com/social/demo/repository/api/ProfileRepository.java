package ua.com.social.demo.repository.api;

import ua.com.social.demo.entity.impl.Profile;

public interface ProfileRepository extends EntityRepository<Profile> {

    void changeOnlineStatus(Integer profileId, Integer onlineStatus) throws Exception;
}
