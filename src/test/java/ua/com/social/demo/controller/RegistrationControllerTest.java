package ua.com.social.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Type;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RegistrationControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void registration() throws Exception {
        mockMvc.perform(post("/registration").content("{\n" +
                "\t\"email\":\"andriy@gmail.com\",\n" +
                "\t\"password\":\"$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni\",\n" +
                "\t\"firstName\":\"Serhiy\",\n" +
                "\t\"lastName\":\"Da\",\n" +
                "\t\"sex\":\"male\",\n" +
                "\t\"age\":\"25\"\n" +
                "}").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
    }

}