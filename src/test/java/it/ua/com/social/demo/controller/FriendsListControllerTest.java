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
import ua.com.social.demo.entity.impl.Friend;
import ua.com.social.demo.entity.impl.FriendList;
import ua.com.social.demo.service.impl.FriendListServiceImpl;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class FriendsListControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private Filter springSecurityFilterChain;
    @MockBean
    private FriendListServiceImpl friendListService;
    private FriendList friendList;
    private Integer profileId;
    private List<Friend> friends = Arrays.asList(new Friend(2, "Ura", "Atamanchyk"), new Friend(3, "Andriy", "Melnik"));

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
        Mockito.when(friendListService.addFriend(friendList)).thenReturn(true);
        Mockito.when(friendListService.getFriendList(profileId)).thenReturn(friends);
        Mockito.when(friendListService.delete(1, 2)).thenReturn(true);
    }

    @Test
    public void login_addFriend_getFriendList_deleteFriend() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/login").content("{\n" +
                "\t\"email\":\"pro@gmail.com\",\n" +
                "\t\"password\":\"1111\"\n" +
                "}").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();
        String token = mvcResult.getResponse().getHeader("Authentication");
        assertNotEquals("", token);
        mockMvc.perform(post("/api/profile/1/add-friend/3").header("Authentication", token))
                .andExpect(status().is(201));
        mockMvc.perform(get("/api/profile/1/friends").header("Authentication", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
        mockMvc.perform(delete("/api/profile/1/delete-friend/3").header("Authentication", token))
                .andExpect(status().isOk());
    }

}