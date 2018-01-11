package it.ua.com.social.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.ProfileDetails;
import ua.com.social.demo.service.ProfileDetailsService;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)

public class ProfileDetailsControllerTest {
    @MockBean
    private ProfileDetailsService profileDetailsService;
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        ProfileDetails profileDetails = new ProfileDetails();
        profileDetails.setProfileId(1);
        profileDetails.setFirstName("Test");
        profileDetails.setLastName("Test");
        profileDetails.setBirthDay(LocalDate.of(1992, 03, 16));
        profileDetails.setSex("male");
        Mockito.when(profileDetailsService.get(profileDetails.getProfileId()))
                .thenReturn(profileDetails);
        Mockito.when(profileDetailsService.update(profileDetails)).thenReturn(true);
    }

    @Test
    public void login_reviewProfile_update() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/login").content("{\n" +
                "\t\"email\":\"pro@gmail.com\",\n" +
                "\t\"password\":\"1111\"\n" +
                "}").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();
        String token = mvcResult.getResponse().getHeader("Authentication");
        mockMvc.perform(get("/api/profile/1")
                .header("Authentication", token))
                .andExpect(status().isOk()).
                andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("firstName").value("Test"))
                .andExpect(jsonPath("lastName").value("Test"));
        mockMvc.perform(put("/api/profile/1/update")
                .header("Authentication", token)
                .content("{\"firstName\":\"Rostyslav\",\"lastName\":\"Paliuha\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
        mockMvc.perform(put("/api/profile/2/update")
                .header("Authentication", token)
                .content("{\"firstName\":\"Rostyslav\",\"lastName\":\"Paliuha\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(403));

    }


}