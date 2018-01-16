package ua.com.social.demo.repository.api;

import ua.com.social.demo.entity.impl.Friend;
import ua.com.social.demo.entity.impl.FriendList;

import java.util.List;

public interface FriendsListRepository extends EntityRepository<FriendList> {

    Integer create(FriendList friendsList) throws Exception;

    void deleteByProfileIdFriendProfileId(Integer profileId, Integer friendProfileId) throws Exception;

    List<Friend> getFriends(Integer profileId) throws Exception;
}
