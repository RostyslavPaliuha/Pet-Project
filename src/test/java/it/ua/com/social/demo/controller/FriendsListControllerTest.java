package it.ua.com.social.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.Friend;
import ua.com.social.demo.entity.impl.FriendList;
import ua.com.social.demo.service.api.FriendListService;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class FriendsListControllerTest extends LoginControllerTest {
    @MockBean
    private FriendListService friendListService;
    private FriendList friendList;
    private List<Friend> friends = Arrays.asList(new Friend(2, "Ura", "Atamanchyk"), new Friend(3, "Andriy", "Melnik"));

    public FriendsListControllerTest() {
        friendList = new FriendList();
        friendList.setFriendProfileId(2);
        friendList.setFriendProfileId(1);
        friendList.setId(1);
    }

    @Test
    public void login_addFriend_getFriendList_deleteFriend() throws Exception {
        Mockito.when(friendListService.addFriend(friendList)).thenReturn(true);
        Mockito.when(friendListService.getFriendList(1)).thenReturn(friends);
        Mockito.when(friendListService.delete(1, 2)).thenReturn(true);
        login();
        String token = super.getHeader();
        mockMvc.perform(post("/api/profile/1/add-friend/3").header("Authentication", token))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/profile/1/friends").header("Authentication", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
        mockMvc.perform(delete("/api/profile/1/delete-friend/3").header("Authentication", token))
                .andExpect(status().isOk());
    }

}