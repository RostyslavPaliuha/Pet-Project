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
import ua.com.social.demo.repository.api.AccountRepository;
import ua.com.social.demo.repository.api.ProfileRepository;
import ua.com.social.demo.service.impl.RegistrationServiceImpl;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})
public class ProfileRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    private Account account;
    private Profile profile;

    public ProfileRepositoryTest() {
        this.account = new Account("testAccount@gmail.com", "$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni", RegistrationServiceImpl.prepareActivateLink());
        this.profile = new Profile();
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void persist_GetByAccountId_Delete() throws Exception {
        Integer accountId = accountRepository.create(account);
        profile.setAccountId(accountId);
        profile.setOnlineStatus(0);
        Integer persistedProfileId = profileRepository.create(profile);
        Profile actualProfile = profileRepository.read(accountId);
        assertEquals(profile.getAccountId(), actualProfile.getAccountId());
        assertEquals(profile.getOnlineStatus(), actualProfile.getOnlineStatus());
        assertEquals(persistedProfileId, new Integer(4));

        profileRepository.read(10000000);
    }
}