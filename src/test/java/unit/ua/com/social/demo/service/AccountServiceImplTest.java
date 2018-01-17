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
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.service.api.AccountService;

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

    @Before
    public void setUp() {
        accountService.persist(new Account("test@gmail.com", "$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni"));
    }

    @Test
    public void persist() throws Exception {
        Optional optional = accountService.persist(new Account("test1@gmail.com", "$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni"));
        assertTrue(optional.isPresent());
        assertEquals(new Integer(5), optional.get());
    }

    @Test
    public void delete() throws Exception {
        this.persist();
        boolean confirmation = accountService.delete(4);
        assertTrue(confirmation);
    }

    @Test
    public void get() throws Exception {
        this.persist();
        Optional<Account> actualAccount = accountService.get(4);
        assertTrue(actualAccount.isPresent());
        assertEquals("test@gmail.com", actualAccount.get().getEmail());
        assertEquals("$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni", actualAccount.get().getPassword());
    }

    @Test
    public void getByEmail() throws Exception {
        this.persist();
        Account account = new Account();
        account.setEmail("test@gmail.com");
        Optional<Account> actualAccount = accountService.getByEmail(account.getEmail());
        assertTrue(actualAccount.isPresent());
        assertEquals(new Integer(4), new Integer(actualAccount.get().getAccountId()));
        assertEquals("$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni", actualAccount.get().getPassword());
    }

}