package ua.com.social.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.dto.FullProfileDto;
import ua.com.social.demo.entity.impl.*;
import ua.com.social.demo.repository.impl.AccountRepository;
import ua.com.social.demo.repository.impl.AlbumRepository;
import ua.com.social.demo.repository.impl.ProfileDetailsRepository;
import ua.com.social.demo.repository.impl.ProfileRepository;
import ua.com.social.demo.service.RegistrationService;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileDetailsRepository profileDetailsRepository;
    @Autowired
    private AlbumRepository albumRepository;

    public void register(FullProfileDto fullProfileDto) {
        Account account = new Account();
        account.setEmail(fullProfileDto.getEmail());
        account.setPassword(fullProfileDto.getPassword());
        Integer accountId = accountRepository.persistAndRetrieveId(account);
        Profile profile = new Profile();
        profile.setAccountId(accountId);
        Integer profileId = profileRepository.persistAndRetrieveId(profile);
        ProfileDetails profileDetails = new ProfileDetails();
        profileDetails.setFirstName(fullProfileDto.getFirstName());
        profileDetails.setLastName(fullProfileDto.getLastName());
        profileDetails.setAge(fullProfileDto.getAge());
        profileDetails.setSex(fullProfileDto.getSex());
        profileDetails.setProfileId(profileId);
        Integer profileDetailId = profileDetailsRepository.persistAndRetrieveId(profileDetails);
        Album album=new Album();
        album.setProfileId(profileId);
        album.setAlbumName("Default");
        albumRepository.persistAndRetrieveId(album);
    }
}
