package it.ua.com.social.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.social.demo.DemoApplication;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ProfileDetailsControllerTest extends LoginControllerTest {
    @Test
    public void login_reviewProfile_update() throws Exception {
        Mockito.when(profileDetailsService.get(profileDetails.getProfileId()))
                .thenReturn(Optional.ofNullable(profileDetails));
        Mockito.when(profileDetailsService.update(profileDetails)).thenReturn(true);
        login();
        String token = getHeader();
        mockMvc.perform(get("/api/profile/1")
                .header("Authentication", token))
                .andExpect(status().isOk()).
                andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("firstName").value("Rostyslav"))
                .andExpect(jsonPath("lastName").value("Paliuha"));
        mockMvc.perform(put("/api/profile/1/update")
                .header("Authentication", token)
                .content("{\"firstName\":\"RostyslavUpdate\",\"lastName\":\"PaliuhaUpdate\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
        mockMvc.perform(put("/api/profile/2/update")
                .header("Authentication", token)
                .content("{\"firstName\":\"Rostyslav\",\"lastName\":\"Paliuha\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(403));

    }


}