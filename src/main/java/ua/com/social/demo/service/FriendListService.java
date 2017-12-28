package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Friend;
import ua.com.social.demo.entity.impl.FriendList;
import ua.com.social.demo.entity.impl.Photo;

import java.util.List;

public interface FriendListService {
    public boolean addFriend(FriendList friendList);

    public boolean delete(FriendList friendList);

    public List<Friend> getFriendList(Integer id);

}
