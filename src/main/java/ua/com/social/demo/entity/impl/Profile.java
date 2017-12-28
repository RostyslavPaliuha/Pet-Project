package ua.com.social.demo.entity.impl;

import ua.com.social.demo.entity.DomainObject;

public class Profile implements DomainObject {
    private Integer profileId;
    private Integer accountId;
    private Integer onlineStatus;

    public Profile(Integer profileId, Integer accountId, Integer onlineStatus) {
        this.profileId = profileId;
        this.accountId = accountId;
        this.onlineStatus = onlineStatus;
    }

    public Profile() {
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
}
