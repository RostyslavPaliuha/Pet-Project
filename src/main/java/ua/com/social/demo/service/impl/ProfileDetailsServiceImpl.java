package ua.com.social.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Profile;
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.repository.impl.ProfileDetailsRepository;
import ua.com.social.demo.service.ProfileDetailsService;

@Service
public class ProfileDetailsServiceImpl implements ProfileDetailsService {
    @Autowired
    private ProfileDetailsRepository profileDetailsRepository;

    @Override
    public boolean persist(ProfileDetails profileDetails) {

        return false;
    }

    @Override
    public ProfileDetails get(Integer id) {
        return profileDetailsRepository.get(id);
    }

    @Override
    public boolean update(ProfileDetails profileDetails) {
        try {
            profileDetailsRepository.update(profileDetails);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }
}
