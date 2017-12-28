package ua.com.social.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.social.demo.entity.impl.Friend;
import ua.com.social.demo.entity.impl.FriendList;
import ua.com.social.demo.service.impl.FriendListServiceImpl;

import java.util.List;

@RestController
public class FriendsListController {
    @Autowired
    private FriendListServiceImpl friendListService;

    @GetMapping("api/profile/{id}/friends")
    @ResponseStatus(HttpStatus.OK)
    public List<Friend> getFriendList(@PathVariable("id") Integer id) {
        return friendListService.getFriendList(id);
    }

    @PostMapping("api/profile/{profileId}/add-friend/{friendId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewFriend(@PathVariable("profileId") Integer profileId, @PathVariable("friendId") Integer friendProfileId) {
        FriendList friendList = new FriendList();
        friendList.setProfileId(profileId);
        friendList.setFriendProfileId(friendProfileId);
        friendListService.addFriend(friendList);
    }

    @DeleteMapping("api/profile/{profileId}/delete-friend/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFriend(@PathVariable("profileId") Integer profileId, @PathVariable("friendId") Integer friendProfileId) {
        FriendList friendList = new FriendList();
        friendList.setProfileId(profileId);
        friendList.setFriendProfileId(friendProfileId);
        friendListService.delete(friendList);
    }
}
