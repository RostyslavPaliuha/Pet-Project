package it.ua.com.social.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.Account;
import ua.com.social.demo.entity.impl.Profile;
import ua.com.social.demo.service.api.AccountService;
import ua.com.social.demo.service.api.ProfileService;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class LoginControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    private String header;
    @MockBean
    AccountService accountService;
    @MockBean
    ProfileService profileService;

    public LoginControllerTest() {
        Account account = new Account();
        account.setEmail("rostyslavpaliuha@gmail.com");
        account.setPassword("$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni");
        account.setAccountId(1);
        account.setActivate(1);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        Mockito.when(accountService.getByEmail("rostyslavpaliuha@gmail.com")).thenReturn(Optional.of(account));
        Mockito.when(profileService.get(1)).thenReturn(Optional.of(new Profile()));
    }

    @Before
    public void setup() {
    }

    @Test
    public void login() throws Exception {
        String url = new StringBuilder().append("/auth/login").toString();
        MvcResult mvcResult = mockMvc.perform(post(url).content("{\n" +
                "\t\"email\":\"rostyslavpaliuha@gmail.com\",\n" +
                "\t\"password\":\"1111\"\n" +
                "}").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();
        header = mvcResult.getResponse().getHeader("Authentication");
        assertNotNull(header);
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    public String getHeader() {
        return header;
    }
}