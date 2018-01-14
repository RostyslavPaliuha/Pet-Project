package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Friend;
import ua.com.social.demo.entity.impl.FriendList;
import ua.com.social.demo.repository.impl.FriendListRepository;
import ua.com.social.demo.service.FriendListService;

import java.util.Collections;
import java.util.List;

@Service("friendListService")
public class FriendListServiceImpl implements FriendListService {
    private static final Logger LOG = Logger.getLogger(FriendListServiceImpl.class);
    @Autowired
    private FriendListRepository friendListRepository;

    @Override
    public boolean addFriend(FriendList friendList) {
        try {
            friendListRepository.persist(friendList);
            return true;
        } catch (Exception e) {
            LOG.error("Error while adding friend" + e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean delete(Integer profileId, Integer friendId) {
        try {
            friendListRepository.delete(profileId, friendId);
            return true;
        } catch (Exception e) {
            LOG.error("Error while deleting friend" + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Friend> getFriendList(Integer profileId) {
        try {
            return friendListRepository.getFriends(profileId);
        } catch (Exception e) {
            LOG.error("Error while retrieve friend list" + e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}
