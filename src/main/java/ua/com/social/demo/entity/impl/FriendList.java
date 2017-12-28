package ua.com.social.demo.entity.impl;

import ua.com.social.demo.entity.DomainObject;

public class FriendList implements DomainObject {
    private int id;
    private int profileId;
    private int friendProfileId;

    public FriendList(int profileId, int friendProfileId) {
        this.profileId = profileId;
        this.friendProfileId = friendProfileId;
    }

    public FriendList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getFriendProfileId() {
        return friendProfileId;
    }

    public void setFriendProfileId(int friendProfileId) {
        this.friendProfileId = friendProfileId;
    }
}
