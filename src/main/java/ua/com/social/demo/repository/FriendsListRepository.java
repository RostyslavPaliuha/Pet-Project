package ua.com.social.demo.repository;

import ua.com.social.demo.entity.DomainObject;
import ua.com.social.demo.entity.impl.Friend;

import java.util.List;

public interface FriendsListRepository<T extends DomainObject> {

    void persist(T fr);

    void delete(Integer profileId, Integer friendProfileId);

    List<Friend> getFriends(Integer id);
}
