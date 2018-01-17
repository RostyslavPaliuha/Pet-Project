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
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.service.api.ProfileDetailsService;

import java.time.LocalDate;
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
public class ProfileDetailsServiceImplTest {
    @Autowired
    private ProfileDetailsService detailsService;
    private ProfileDetails details;

    @Before
    public void setUp() {
        details = new ProfileDetails();
        details.setFirstName("");
        details.setLastName("");
        details.setBirthDay(LocalDate.of(1992, 03, 16));
        details.setProfileId(4);
        details.setSex("male");
        details.setCountry("Ukraine");
    }

    @Test
    public void persist() throws Exception {
        Optional<Integer> actualProfileDetailsId = detailsService.persist(details);
        assertTrue(actualProfileDetailsId.isPresent());
        assertEquals(actualProfileDetailsId.get(), new Integer(4));
    }

    @Test
    public void get() throws Exception {
        this.persist();
        Optional<ProfileDetails> actualProfileDetailsOptional = detailsService.get(4);
        assertTrue(actualProfileDetailsOptional.isPresent());
        assertEquals("", actualProfileDetailsOptional.get().getFirstName());
    }

    @Test
    public void update() throws Exception {
        Boolean confirmation = detailsService.update(details);
        assertTrue(confirmation);
    }

}