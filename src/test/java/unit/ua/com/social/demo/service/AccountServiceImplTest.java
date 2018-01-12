package unit.ua.com.social.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.service.AccountService;

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

public class AccountServiceImplTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void persist() throws Exception {
        Optional optional = accountService.persist("test@gmail.com", "1111");
        assertTrue(optional.isPresent());
        assertEquals(new Integer(4), optional.get());
    }

    @Test
    public void delete() throws Exception {
        this.persist();
        boolean confirmation = accountService.delete(4);
        assertTrue(confirmation);
    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void getByEmail() throws Exception {
    }

}