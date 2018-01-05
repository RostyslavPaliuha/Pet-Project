package ua.com.social.demo.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.social.demo.entity.impl.Account;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test-application.properties")
public class AccountRepositoryTest {
@Autowired
private AccountRepository accountRepository;
    @Test
    public void persist() throws Exception {
        Account account=new Account();
        account.setEmail("test@gmail.com");
        account.setPassword("$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni");
        accountRepository.persist(account);
        Account actual =accountRepository.getByEmail(account);
        assertEquals(account.getEmail(),actual.getEmail());
    }

    @Test
    public void persistAndRetrieveId() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void getByEmail() throws Exception {
    }

}