package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.controller.ExceptionHandlerController;
import ua.com.social.demo.entity.impl.Friend;
import ua.com.social.demo.entity.impl.FriendList;
import ua.com.social.demo.repository.impl.FriendListRepository;
import ua.com.social.demo.service.FriendListService;

import java.util.List;

@Service
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
            LOG.error("Error while saving data" + e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean delete(FriendList friendList) {
        try {
            friendListRepository.delete(friendList);
        } catch (Exception e) {
            LOG.error("Error while delete data" + e.getMessage(), e);
        }
        return false;
    }

    @Override
    public List<Friend> getFriendList(Integer id) {
        try{
            return friendListRepository.getFriends(id);
        }catch (Exception e){
            LOG.error("Error while retrieve data" + e.getMessage(), e);
        }
        return null;
    }
}
