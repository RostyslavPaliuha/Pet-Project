package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Profile;
import ua.com.social.demo.repository.impl.ProfileRepository;
import ua.com.social.demo.service.ProfileService;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
    private static final Logger LOG = Logger.getLogger(ProfileServiceImpl.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Integer persist(Profile profile) {
        try {
            return profileRepository.persistAndRetrieveId(profile);
        } catch (Exception e) {
            LOG.error("Error while saving data" + e.getMessage(), e);
            return new Integer(null);
        }
    }

    @Override
    public Profile get(Integer id) {
        Profile profile = null;
        try {
            return profile = profileRepository.get(id);
        } catch (EmptyResultDataAccessException e) {
            LOG.error("Error while saving data" + e.getMessage(), e);
            return profile;
        }

    }

    @Override
    public boolean update(Profile profile) {
        try {
            profileRepository.changeOnlineStatus(profile.getProfileId(), profile.getOnlineStatus());
            return true;
        } catch (Exception e) {
            LOG.error("Error while saving data" + e.getMessage(), e);
            return false;
        }
    }
}
