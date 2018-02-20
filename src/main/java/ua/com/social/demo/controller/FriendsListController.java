package ua.com.social.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.social.demo.entity.impl.Friend;
import ua.com.social.demo.entity.impl.FriendList;
import ua.com.social.demo.service.api.FriendListService;

import java.util.List;

@RestController
@RequestMapping("api/profile/{profileId}")
public class FriendsListController {
    private static final Logger LOG = Logger.getLogger(FriendsListController.class);
    @Autowired
    private FriendListService friendListService;

    @GetMapping("/friends")
    public ResponseEntity getFriendList(@PathVariable("profileId") Integer id) {
        try {
            List<Friend> friends = friendListService.getFriendList(id);
            return new ResponseEntity(friends, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Get friend list failed " + e.getMessage());
            return new ResponseEntity("Get friend list failed.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-friend/{friendId}")
    public ResponseEntity addNewFriend(@PathVariable("profileId") Integer profileId, @PathVariable("friendId") Integer friendProfileId) {
        try {
            FriendList friendList = new FriendList();
            friendList.setProfileId(profileId);
            friendList.setFriendProfileId(friendProfileId);
            friendListService.addFriend(friendList);
            return new ResponseEntity("Successfully add new friend.", HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Add new friend failed " + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt to add friend.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-friend/{friendId}")
    public ResponseEntity deleteFriend(@PathVariable("profileId") Integer profileId, @PathVariable("friendId") Integer friendProfileId) {
        try {
            friendListService.delete(profileId, friendProfileId);
            return new ResponseEntity("Delete friend successful.", HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Deleting failed " + e.getMessage());
            return new ResponseEntity("Deleting failed.", HttpStatus.BAD_REQUEST);
        }
    }
}
