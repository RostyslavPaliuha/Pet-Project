package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.service.ProfileDetailsService;

@Service
public class ProfileDetailsServiceImpl implements ProfileDetailsService {
    private static final Logger LOG = Logger.getLogger(ProfileDetailsServiceImpl.class);
    @Autowired
    private ua.com.social.demo.repository.ProfileDetailsRepository profileDetailsRepository;

    @Override
    public boolean persist(ProfileDetails profileDetails) {
        return false;
    }

    @Override
    public ProfileDetails get(Integer id) {
        try {
            return profileDetailsRepository.get(id);
        } catch (EmptyResultDataAccessException e) {
            LOG.error("Error while saving data" + e.getMessage(), e);
            return null;
        } catch (Exception e) {
            LOG.error("Error while saving data" + e.getMessage(), e);
            return null;
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
