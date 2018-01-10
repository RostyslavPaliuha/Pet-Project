package it.ua.com.social.demo.repository.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.repository.impl.AccountRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})
public class AccountRepositoryTest {
    @Autowired

    private AccountRepository accountRepository;

    private Account account;

    public AccountRepositoryTest() {
        account = new Account();
        account.setEmail("test@gmail.com");
        account.setPassword("$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni");
    }

    @Test
    public void persist_andGetByEmail() throws Exception {
        accountRepository.persist(account);
        Account actual = accountRepository.getByEmail(account);
        assertEquals(account.getEmail(), actual.getEmail());
    }

    @Test
    public void persistAndRetrieveId() throws Exception {
        account.setEmail("test2@gmail.com");
        Integer id = accountRepository.persistAndRetrieveId(account);
        assertEquals(new Integer(4), id);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void delete() throws Exception {
        account.setEmail("pro@gmail.com");
        accountRepository.delete(new Account(1));
        accountRepository.getByEmail(account);

    }
    @Test
    public void update_get_assert(){
        Account andriy=new Account();
        andriy.setEmail("andriyMelnik@gmail.com");
        andriy.setPassword("$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni");
        accountRepository.updateEmail(andriy.getEmail(),3);
        Account actual=accountRepository.getByEmail(andriy);
        assertEquals(andriy.getEmail(),actual.getEmail());
        assertEquals(andriy.getPassword(),actual.getPassword());
        andriy.setPassword("$2y$10$P7c2zGPWZ9LLQPZiIhpJOu4WgglNabfottvvJkS1TewCSZitIfsbG");
        accountRepository.updatePassword(andriy.getPassword(),3);
    }


}