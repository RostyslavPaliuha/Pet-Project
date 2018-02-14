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
import ua.com.social.demo.repository.api.AccountRepository;
import ua.com.social.demo.repository.api.ProfileDetailsRepository;
import ua.com.social.demo.repository.api.ProfileRepository;

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
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileDetailsRepository detailsRepository;
    private Account account;
    private Profile profile;
    private ProfileDetails profileDetails;
    private ProfileDetails updateProfileDetails;

    public ProfileDetailsRepositoryTest() {
        this.account = new Account("testAccount@gmail.com", "$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni");
        this.profile = new Profile();
        this.profileDetails = new ProfileDetails("testName", "testLastNAme", ProfileDetails.Sex.male, LocalDate.of(1992, 03, 16));
        updateProfileDetails = new ProfileDetails("UpdatedTestName", "UpdatedTestLastNAme", ProfileDetails.Sex.male, LocalDate.of(1992, 03, 16));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void persist_Get_Update() throws Exception {
        Integer accountId = accountRepository.create(new Account(account.getEmail(), account.getPassword()));
        profile.setAccountId(accountId);
        Integer profileId = profileRepository.create(profile);
        profileDetails.setProfileId(profileId);
        Integer profileDetailsId = detailsRepository.create(profileDetails);
        ProfileDetails actualProfileDetails = detailsRepository.read(profileId);
        assertEquals(new Integer(profileDetails.getProfileId()), new Integer(actualProfileDetails.getProfileId()));
        assertEquals(profileDetails.getFirstName(), actualProfileDetails.getFirstName());
        assertEquals(profileDetails.getLastName(), actualProfileDetails.getLastName());
        assertEquals(profileDetails.getSex(), actualProfileDetails.getSex());
        updateProfileDetails.setProfileId(profileId);
        updateProfileDetails.setProfileDetailsId(profileDetailsId);
        detailsRepository.update(updateProfileDetails);
        ProfileDetails actualUpdatedProfileDetails = detailsRepository.read(profileId);
        assertEquals(new Integer(actualUpdatedProfileDetails.getProfileDetailsId()), new Integer(profileDetailsId));
        assertEquals(new Integer(actualUpdatedProfileDetails.getProfileId()), new Integer(updateProfileDetails.getProfileId()));
        assertEquals(actualUpdatedProfileDetails.getFirstName(), updateProfileDetails.getFirstName());
        assertEquals(actualUpdatedProfileDetails.getLastName(), updateProfileDetails.getLastName());
        assertEquals(actualUpdatedProfileDetails.getSex(), updateProfileDetails.getSex());
       /* Must delete and throw EmptyResultDataAccessException */
        detailsRepository.delete(updateProfileDetails.getProfileId());
        ProfileDetails checkProfileDetailsAfterDelete = detailsRepository.read(profileId);
    }

}