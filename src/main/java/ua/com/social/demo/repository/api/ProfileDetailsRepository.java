package ua.com.social.demo.repository.api;

import ua.com.social.demo.entity.impl.ProfileDetails;

public interface ProfileDetailsRepository extends EntityRepository<ProfileDetails> {

    void update(ProfileDetails profileDetails) throws Exception;

}
