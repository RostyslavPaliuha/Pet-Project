package unit.ua.com.social.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.Friend;
import ua.com.social.demo.entity.impl.FriendList;
import ua.com.social.demo.service.FriendListService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})
public class FriendListServiceImplTest {
    @Autowired
    private FriendListService friendListService;
    private FriendList friendList;

    @Before
    public void setUp() {
        friendList = new FriendList();
        friendList.setProfileId(1);
        friendList.setFriendProfileId(3);
    }

    @Test
    public void addFriend() throws Exception {
        Boolean confirmation = friendListService.addFriend(friendList);
        assertTrue(confirmation);
    }

    @Test
    public void delete() throws Exception {
        this.addFriend();
        Boolean confirmation = friendListService.delete(1, 3);
        assertTrue(confirmation);
    }

    @Test
    public void getFriendList() throws Exception {
        this.addFriend();
        List<Friend> actualList = friendListService.getFriendList(1);
        assertEquals(new Integer(2), new Integer(actualList.size()));

    }

}