package it.ua.com.social.demo.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.*;
import ua.com.social.demo.repository.api.AccountRepository;
import ua.com.social.demo.repository.api.ProfileDetailsRepository;
import ua.com.social.demo.repository.api.ProfileRepository;
import ua.com.social.demo.repository.impl.FriendListRepositoryImpl;
import ua.com.social.demo.service.impl.RegistrationServiceImpl;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})

public class FriendListRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileDetailsRepository detailsRepository;
    @Autowired
    private FriendListRepositoryImpl friendListRepository;

    private Account account;
    private Profile profile;
    private ProfileDetails profileDetails;
    private FriendList friendList;

    public FriendListRepositoryTest() {
        this.account = new Account("testAccount@gmail.com", "$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni", RegistrationServiceImpl.prepareActivateLink());
        this.profile = new Profile();
        this.profileDetails = new ProfileDetails("testName", "testLastNAme", ProfileDetails.Sex.male, LocalDate.of(1992, 03, 16));
        this.friendList = new FriendList();
    }

    @Test
    public void addFriend_checkFriends_deleteFriend_checkFriends() throws Exception {
        friendList.setProfileId(1);
        friendList.setFriendProfileId(3);
        friendListRepository.create(friendList);
        List<Friend> friends = friendListRepository.getFriends(3);
        assertEquals(new Integer(1), new Integer(friends.size()));
        assertEquals("Rostyslav", friends.get(0).getFirstName());

        List<Friend> myFriendFriends = friendListRepository.getFriends(1);
        assertEquals(new Integer(2), new Integer(myFriendFriends.size()));
        assertEquals(myFriendFriends.get(1).getFirstName(), "Andriy");
        friendListRepository.deleteByProfileIdFriendProfileId(friendList.getProfileId(), friendList.getFriendProfileId());

        List<Friend> afterDeleteFriendList = friendListRepository.getFriends(3);
        assertEquals(new Integer(0), new Integer(afterDeleteFriendList.size()));

        List<Friend> myFriendFriendsAfterDelete = friendListRepository.getFriends(1);
        assertEquals(new Integer(1), new Integer(myFriendFriendsAfterDelete.size()));
    }


}