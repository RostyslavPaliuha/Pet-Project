package unit.ua.com.social.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.dto.FullProfileDto;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Profile;
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.repository.api.AccountRepository;
import ua.com.social.demo.repository.api.AlbumRepository;
import ua.com.social.demo.repository.api.ProfileDetailsRepository;
import ua.com.social.demo.repository.api.ProfileRepository;
import ua.com.social.demo.service.api.RegistrationService;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})
public class RegistrationServiceImplTest {
    @Autowired
    private RegistrationService registrationService;
    private FullProfileDto profileDto;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileDetailsRepository profileDetailsRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        profileDto = new FullProfileDto();
        profileDto.setFirstName("TEST ACCOUNT");
        profileDto.setLastName("TEST ACCOUNT");
        profileDto.setBirthday(LocalDate.of(1992, 03, 16));
        profileDto.setEmail("test@gmail.com");
        profileDto.setPassword("1111");
        profileDto.setSex("male");
    }

    @Test
    public void register() throws Exception {
        registrationService.register(profileDto);
        Account actualAccount = accountRepository.getByEmail("test@gmail.com");
        assertEquals(new Integer(4), new Integer(actualAccount.getAccountId()));
        assertTrue(passwordEncoder.matches("1111", actualAccount.getPassword()));
        Profile actualProfile = profileRepository.read(4);
        assertEquals(new Integer(4), actualProfile.getProfileId());
        ProfileDetails actualProfileDetails = profileDetailsRepository.read(4);
        assertEquals("TEST ACCOUNT", actualProfileDetails.getFirstName());
        assertEquals("TEST ACCOUNT", actualProfileDetails.getLastName());
        Album actualAlbum = albumRepository.read(2);
        assertEquals("Default", actualAlbum.getAlbumName());
    }

}