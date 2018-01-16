package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.dto.FullProfileDto;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Profile;
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.repository.api.AccountRepository;
import ua.com.social.demo.repository.api.AlbumRepository;
import ua.com.social.demo.repository.api.ProfileDetailsRepository;
import ua.com.social.demo.repository.api.ProfileRepository;
import ua.com.social.demo.service.RegistrationService;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {
    private static final Logger LOG = Logger.getLogger(RegistrationService.class);
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileDetailsRepository profileDetailsRepository;
    @Autowired
    private AlbumRepository albumRepository;

    public void register(FullProfileDto fullProfileDto) {
        try {
            Integer accountId = accountRepository.create(new Account(fullProfileDto.getEmail(), fullProfileDto.getPassword()));
            Profile profile = new Profile();
            profile.setAccountId(accountId);
            Integer profileId = profileRepository.create(profile);
            ProfileDetails profileDetails = new ProfileDetails();
            profileDetails.setFirstName(fullProfileDto.getFirstName());
            profileDetails.setLastName(fullProfileDto.getLastName());
            profileDetails.setBirthDay(fullProfileDto.getBirthday());
            profileDetails.setSex(fullProfileDto.getSex());
            profileDetails.setProfileId(profileId);
            Integer profileDetailId = profileDetailsRepository.create(profileDetails);
            Album album = new Album();
            album.setProfileId(profileId);
            album.setAlbumName("Default");
            albumRepository.create(album);
        } catch (Exception e) {
            LOG.error("Error while registering profile" + e.getMessage(), e);
        }
    }
}
