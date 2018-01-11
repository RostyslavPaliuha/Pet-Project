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
import ua.com.social.demo.repository.ProfileRepository;
import ua.com.social.demo.repository.impl.FriendListRepository;

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
    private ua.com.social.demo.repository.AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ua.com.social.demo.repository.ProfileDetailsRepository detailsRepository;
    @Autowired
    private FriendListRepository friendListRepository;
    private Account account;
    private Profile profile;
    private ProfileDetails profileDetails;
    private FriendList friendList;

    public FriendListRepositoryTest() {
        this.account = new Account("testAccount@gmail.com", "$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni");
        this.profile = new Profile();
        this.profileDetails = new ProfileDetails("testName", "testLastNAme", Sex.male, LocalDate.of(1992, 03, 16));
        this.friendList = new FriendList();
    }

    @Test
    public void addFriend_checkFriends_deleteFriend_checkFriends() throws Exception {
        Integer accountId = accountRepository.persistAndRetrieveId(account);
        profile.setAccountId(accountId);
        profile.setOnlineStatus(0);
        Integer profileId = profileRepository.persistAndRetrieveId(profile);
        profileDetails.setProfileId(profileId);
        Integer profileDetailsId = detailsRepository.persistAndRetrieveId(profileDetails);
        ProfileDetails actualProfileDetails = detailsRepository.get(profileId);
        friendList.setProfileId(profileId);
        friendList.setFriendProfileId(1);
        friendListRepository.persist(friendList);

        List<Friend> friends = friendListRepository.getFriends(profileId);
        assertEquals(new Integer(1), new Integer(friends.size()));
        assertEquals("Rostyslav", friends.get(0).getFirstName());

        List<Friend> myFriendFriends = friendListRepository.getFriends(1);
        assertEquals(new Integer(2), new Integer(myFriendFriends.size()));
        assertEquals(myFriendFriends.get(1).getFirstName(), "testName");
        friendListRepository.delete(friendList.getProfileId(), friendList.getFriendProfileId());

        List<Friend> afterDeleteFriendList = friendListRepository.getFriends(profileId);
        assertEquals(new Integer(0), new Integer(afterDeleteFriendList.size()));

        List<Friend> myFriendFriendsAfterDelete = friendListRepository.getFriends(1);
        assertEquals(new Integer(1), new Integer(myFriendFriendsAfterDelete.size()));
    }


}