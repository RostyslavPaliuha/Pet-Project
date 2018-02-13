package it.ua.com.social.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.dto.FullProfileDto;
import ua.com.social.demo.service.api.RegistrationService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)

public class RegistrationControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @SpyBean
    private RegistrationService registrationService;
    private FullProfileDto fullProfileDto;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        doNothing().when(registrationService).register(any());
    }

    @Test
    public void registration() throws Exception {
        mockMvc.perform(post("/auth/registration").content("{\n" +
                "\t\"email\":\"andriymelnik@gmail.com\",\n" +
                "\t\"password\":\"$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni\",\n" +
                "\t\"firstName\":\"Andriy\",\n" +
                "\t\"lastName\":\"Melnik\",\n" +
                "\t\"sex\":\"male\",\n" +
                "\t\"age\":\"1992-01-01\"\n" +
                "}").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
    }

}