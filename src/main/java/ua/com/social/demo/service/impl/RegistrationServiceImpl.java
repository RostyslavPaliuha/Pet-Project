package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.social.demo.dto.FullProfileDto;
import ua.com.social.demo.entity.impl.*;
import ua.com.social.demo.repository.api.*;
import ua.com.social.demo.service.api.RegistrationService;
import ua.com.social.demo.service.api.StorageService;

import java.security.InvalidParameterException;


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
    private PostRepository postRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private StorageService storageService;

    public void register(FullProfileDto fullProfileDto) {
        try {
            Integer accountId = accountRepository.create(new Account(fullProfileDto.getEmail(), encoder.encode(fullProfileDto.getPassword())));
            Profile profile = new Profile();
            profile.setAccountId(accountId);
            Integer profileId = profileRepository.create(profile);
            ProfileDetails profileDetails = new ProfileDetails();
            profileDetails.setFirstName(fullProfileDto.getFirstName());
            profileDetails.setLastName(fullProfileDto.getLastName());
            profileDetails.setBirthDay(fullProfileDto.getBirthday());
            profileDetails.setSex(fullProfileDto.getSex());
            profileDetails.setCountry(fullProfileDto.getCountry());
            profileDetails.setProfileId(profileId);
            Integer profileDetailId = profileDetailsRepository.create(profileDetails);
            storageService.mkDirForNewUser(profileId);
        }catch (DuplicateKeyException dke){
            throw new InvalidParameterException("This email is used, try differently. "+dke.getMessage());
        } catch (Exception e) {
            LOG.error("Error while registering profile" + e.getMessage(), e);
        }
    }
}
