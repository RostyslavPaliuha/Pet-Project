package ua.com.social.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.dto.FullProfileDto;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Profile;
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.repository.ProfileRepository;
import ua.com.social.demo.service.RegistrationService;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private ua.com.social.demo.repository.AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ua.com.social.demo.repository.ProfileDetailsRepository profileDetailsRepository;
    @Autowired
    private ua.com.social.demo.repository.AlbumRepository albumRepository;

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
        profileDetails.setBirthDay(fullProfileDto.getBirthday());
        profileDetails.setSex(fullProfileDto.getSex());
        profileDetails.setProfileId(profileId);
        Integer profileDetailId = profileDetailsRepository.persistAndRetrieveId(profileDetails);
        Album album = new Album();
        album.setProfileId(profileId);
        album.setAlbumName("Default");
        albumRepository.persistAndRetrieveId(album);
    }
}
