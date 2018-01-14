package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.repository.ProfileDetailsRepository;
import ua.com.social.demo.service.ProfileDetailsService;

import java.util.Optional;

@Service("proifileDetailsService")
public class ProfileDetailsServiceImpl implements ProfileDetailsService {
    private static final Logger LOG = Logger.getLogger(ProfileDetailsServiceImpl.class);
    @Autowired
    private ProfileDetailsRepository profileDetailsRepository;

    @Override
    public Optional<Integer> persist(ProfileDetails profileDetails) {
        Optional<Integer> integerOptional = Optional.empty();
        try {
            return Optional.ofNullable(profileDetailsRepository.persistAndRetrieveId(profileDetails));
        } catch (Exception e) {
            LOG.error("Error while saving data" + e.getMessage(), e);
            return integerOptional;
        }
    }

    @Override
    public Optional<ProfileDetails> get(Integer id) {
        try {
            return Optional.ofNullable(profileDetailsRepository.get(id));
        } catch (EmptyResultDataAccessException e) {
            LOG.error("Error while getting profile details" + e.getMessage(), e);
            return Optional.empty();
        } catch (Exception e) {
            LOG.error("Error while getting profile details" + e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public boolean update(ProfileDetails profileDetails) {
        try {
            profileDetailsRepository.update(profileDetails);
            return true;
        } catch (Exception e) {
            LOG.error("Error while saving data" + e.getMessage(), e);
            return false;
        }
    }
}
