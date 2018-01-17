package unit.ua.com.social.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.Profile;
import ua.com.social.demo.service.api.ProfileService;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})
public class ProfileServiceImplTest {
    @Autowired
    private ProfileService profileService;
    private Profile profile;

    @Before
    public void setUp() {
        profile = new Profile();
        profile.setAccountId(1);
        profile.setProfileId(1);
        profile.setOnlineStatus(1);
    }

    @Test
    public void persist() throws Exception {
        Optional<Integer> actulaIntegerOptional = profileService.persist(profile);
        assertTrue(actulaIntegerOptional.isPresent());
        assertEquals(new Integer(4), actulaIntegerOptional.get());
    }

    @Test
    public void get() throws Exception {
        Optional<Profile> actualProfileOptional = profileService.get(1);
        assertTrue(actualProfileOptional.isPresent());
        assertEquals(new Integer(1), actualProfileOptional.get().getAccountId());
    }

    @Test
    public void update() throws Exception {
        profile.setOnlineStatus(0);
        Boolean confirmation = profileService.update(profile);
        assertTrue(confirmation);
        Optional<Profile> actualProfile = profileService.get(1);
        assertTrue(actualProfile.isPresent());
        assertEquals(new Integer(0), actualProfile.get().getOnlineStatus());

    }

}