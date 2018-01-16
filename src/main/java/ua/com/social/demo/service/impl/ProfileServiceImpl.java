package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Profile;
import ua.com.social.demo.repository.api.ProfileRepository;
import ua.com.social.demo.service.ProfileService;

import java.util.Optional;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
    private static final Logger LOG = Logger.getLogger(ProfileServiceImpl.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Optional<Integer> persist(Profile profile) {
        Optional<Integer> integerOptional = Optional.empty();
        try {
            return Optional.ofNullable(profileRepository.create(profile));
        } catch (Exception e) {
            LOG.error("Error while saving profile" + e.getMessage(), e);
            return integerOptional;
        }
    }

    @Override
    public Optional<Profile> get(Integer id) {
        try {
            return Optional.ofNullable(profileRepository.read(id));
        } catch (EmptyResultDataAccessException e) {
            LOG.error("Error while getting profile" + e.getMessage(), e);
            return Optional.empty();
        } catch (Exception e) {
            LOG.error("Error while getting profile" + e.getMessage(), e);
            return Optional.empty();
        }

    }

    @Override
    public boolean update(Profile profile) {
        try {
            profileRepository.changeOnlineStatus(profile.getProfileId(), profile.getOnlineStatus());
            return true;
        } catch (Exception e) {
            LOG.error("Error while updating profile" + e.getMessage(), e);
            return false;
        }
    }
}
