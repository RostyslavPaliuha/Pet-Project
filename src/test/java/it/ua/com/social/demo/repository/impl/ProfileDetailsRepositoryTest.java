package it.ua.com.social.demo.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.entity.impl.Profile;
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.entity.impl.Sex;
import ua.com.social.demo.repository.ProfileRepository;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})
public class ProfileDetailsRepositoryTest {
    @Autowired
    private ua.com.social.demo.repository.AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ua.com.social.demo.repository.ProfileDetailsRepository detailsRepository;
    private Account account;
    private Profile profile;
    private ProfileDetails profileDetails;

    public ProfileDetailsRepositoryTest() {
        this.account = new Account("testAccount@gmail.com", "$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni");
        this.profile = new Profile();
        this.profileDetails = new ProfileDetails("testName", "testLastNAme", Sex.male, LocalDate.of(1992, 03, 16));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void persist_Get_Update() throws Exception {
        Integer accountId = accountRepository.persistAndRetrieveId(account);
        profile.setAccountId(accountId);
        profile.setOnlineStatus(0);
        Integer profileId = profileRepository.persistAndRetrieveId(profile);
        profileDetails.setProfileId(profileId);
        Integer profileDetailsId = detailsRepository.persistAndRetrieveId(profileDetails);
        ProfileDetails actualProfileDetails = detailsRepository.get(profileId);
        assertEquals(profileDetails.getProfileId(), actualProfileDetails.getProfileId());
        assertEquals(profileDetails.getFirstName(), actualProfileDetails.getFirstName());
        assertEquals(profileDetails.getLastName(), actualProfileDetails.getLastName());
        assertEquals(profileDetails.getSex(), actualProfileDetails.getSex());
        ProfileDetails updateProfileDetails = new ProfileDetails("UpdatedTestName", "UpdatedTestLastNAme", Sex.male, LocalDate.of(1992, 03, 16));
        updateProfileDetails.setProfileId(profileId);
        updateProfileDetails.setProfileDetailsId(profileDetailsId);
        detailsRepository.update(updateProfileDetails);
        ProfileDetails actualUpdatedProfileDetails = detailsRepository.get(profileId);
        assertEquals(actualUpdatedProfileDetails.getProfileDetailsId(), profileDetailsId);
        assertEquals(actualUpdatedProfileDetails.getProfileId(), updateProfileDetails.getProfileId());
        assertEquals(actualUpdatedProfileDetails.getFirstName(), updateProfileDetails.getFirstName());
        assertEquals(actualUpdatedProfileDetails.getLastName(), updateProfileDetails.getLastName());
        assertEquals(actualUpdatedProfileDetails.getSex(), updateProfileDetails.getSex());
       /* Must delete and throw EmptyResultDataAccessException */
        detailsRepository.delete(updateProfileDetails.getProfileId());
        ProfileDetails checkProfileDetailsAfterDelete = detailsRepository.get(profileId);
    }

}