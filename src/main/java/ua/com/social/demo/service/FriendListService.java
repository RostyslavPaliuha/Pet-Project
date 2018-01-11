package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Friend;
import ua.com.social.demo.entity.impl.FriendList;

import java.util.List;

public interface FriendListService {
    public boolean addFriend(FriendList friendList);

    public boolean delete(Integer profileId, Integer friendId);

    public List<Friend> getFriendList(Integer id);

}
