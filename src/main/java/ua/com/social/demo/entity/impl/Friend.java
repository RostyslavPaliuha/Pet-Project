package ua.com.social.demo.entity.impl;

import ua.com.social.demo.entity.DomainObject;

public class Friend implements DomainObject{
    private Integer friendProfileId;
    private String firstName;
    private String lastName;

    public Friend(Integer friendProfileId, String firstName, String lastName) {
        this.friendProfileId = friendProfileId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Friend() {
    }

    public Integer getFriendProfileId() {
        return friendProfileId;
    }

    public void setFriendProfileId(Integer friendProfileId) {
        this.friendProfileId = friendProfileId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
