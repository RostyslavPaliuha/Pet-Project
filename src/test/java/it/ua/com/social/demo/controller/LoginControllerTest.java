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
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.service.api.AccountService;
import ua.com.social.demo.service.api.ProfileDetailsService;
import ua.com.social.demo.service.api.ProfileService;

import javax.servlet.Filter;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class LoginControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private Filter springSecurityFilterChain;
    protected MockMvc mockMvc;
    private String header;
    @MockBean
    protected AccountService accountService;
    @MockBean
    protected ProfileService profileService;
    @MockBean
    protected ProfileDetailsService profileDetailsService;
    private Account account;
    private Profile profile;
    protected ProfileDetails profileDetails;

    public LoginControllerTest() {
        account = new Account();
        account.setEmail("rostyslavpaliuha@gmail.com");
        account.setPassword("$2a$10$KAsMQy8ITYn6EGUgw97gtOqRO2E84zPPLuo9UnoS5rioxxCnoSco2");
        account.setAccountId(1);
        account.setActivate(1);
        profile = new Profile();
        profile.setProfileId(1);
        profile.setAccountId(1);
        profileDetails = new ProfileDetails();
        profileDetails.setProfileId(1);
        profileDetails.setFirstName("Rostyslav");
        profileDetails.setLastName("Paliuha");
        profileDetails.setBirthDay(LocalDate.of(1992, 03, 16));
        profileDetails.setSex("male");
        profileDetails.setCountry("IF");
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
        Mockito.when(accountService.getByEmail("rostyslavpaliuha@gmail.com")).thenReturn(Optional.of(account));
        Mockito.when(profileService.get(1)).thenReturn(Optional.of(profile));
        Mockito.when(profileDetailsService.get(1)).thenReturn(Optional.of(profileDetails));
    }

    @Test
    public void login() throws Exception {
        String url = "/auth/login";
        MvcResult mvcResult = mockMvc.perform(post(url).content("{\n" +
                "\t\"email\":\"rostyslavpaliuha@gmail.com\",\n" +
                "\t\"password\":\"16031992pro\"\n" +
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