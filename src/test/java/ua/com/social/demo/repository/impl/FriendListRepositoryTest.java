package ua.com.social.demo.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})

public class FriendListRepositoryTest {
    @Test
    public void persist() throws Exception {

    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void persistAndRetrieveId() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void getFriends() throws Exception {
    }

}