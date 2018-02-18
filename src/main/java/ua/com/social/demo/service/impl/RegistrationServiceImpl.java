package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.social.demo.dto.FullProfileDto;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.entity.impl.Profile;
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.repository.api.AccountRepository;
import ua.com.social.demo.repository.api.PostRepository;
import ua.com.social.demo.repository.api.ProfileDetailsRepository;
import ua.com.social.demo.repository.api.ProfileRepository;
import ua.com.social.demo.service.api.RegistrationService;
import ua.com.social.demo.service.api.StorageService;

import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.util.Random;


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
    @Autowired
    private EmailServiceImpl emailService;

    public void register(FullProfileDto fullProfileDto) {
        Integer hash = prepareActivateLink();
        try {
            Integer accountId = accountRepository.create(new Account(fullProfileDto.getEmail(), encoder.encode(fullProfileDto.getPassword()), hash));
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
            String msg = "Congratulation! This is the last step of registration. To activate your account click on this link: http://localhost:8181/auth/activate/" + fullProfileDto.getEmail() + "/?hash=" + hash;
            emailService.sendSimpleMessage(fullProfileDto.getEmail(), "Activate service message.", msg);
        } catch (DuplicateKeyException dke) {
            throw new InvalidParameterException("This email is used, try differently. " + dke.getMessage());
        } catch (Exception e) {
            LOG.error("Error while registering profile" + e.getMessage(), e);
        }
    }

    public static Integer prepareActivateLink() {
       Integer generatedInt=(int)(Math.random()*1000000);
        return generatedInt;
    }

    public boolean activateAccount(String email, Integer hash) {
        Account account = accountRepository.getByEmail(email);
        if (account.getActivateHash().equals(hash)) {
            accountRepository.updateActivationStatus(email);
            return true;
        } else {
            return false;
        }
    }
}
