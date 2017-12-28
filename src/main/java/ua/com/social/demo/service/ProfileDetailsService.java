package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Profile;
import ua.com.social.demo.entity.impl.ProfileDetails;

public interface ProfileDetailsService {
    public boolean persist(ProfileDetails profileDetails);

    public ProfileDetails get(Integer id);

    public boolean update(ProfileDetails profileDetails);
}
