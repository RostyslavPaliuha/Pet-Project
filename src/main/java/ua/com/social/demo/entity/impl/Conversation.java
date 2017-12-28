package ua.com.social.demo.entity.impl;

import ua.com.social.demo.entity.DomainObject;

import java.time.LocalDateTime;
import java.util.List;

public class Conversation implements DomainObject {
    private int conversationId;
    private int companionId;
    private int profileId;
    private String profileName;
    private String profileLastName;
    private String companionName;
    private String companionLastName;
    private LocalDateTime lastMsgDate;
    private List<Message> messageList;

    public Conversation() {
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getCompanionId() {
        return companionId;
    }

    public void setCompanionId(int companionId) {
        this.companionId = companionId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileLastName() {
        return profileLastName;
    }

    public void setProfileLastName(String profileLastName) {
        this.profileLastName = profileLastName;
    }

    public String getCompanionName() {
        return companionName;
    }

    public void setCompanionName(String companionName) {
        this.companionName = companionName;
    }

    public String getCompanionLastName() {
        return companionLastName;
    }

    public void setCompanionLastName(String companionLastName) {
        this.companionLastName = companionLastName;
    }

    public LocalDateTime getLastMsgDate() {
        return lastMsgDate;
    }

    public void setLastMsgDate(LocalDateTime lastMsgDate) {
        this.lastMsgDate = lastMsgDate;
    }
}
