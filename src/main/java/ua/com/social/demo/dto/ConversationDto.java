package ua.com.social.demo.dto;

import java.time.LocalDateTime;

public class ConversationDto {
    private Integer conversationId;
    private Integer companionId;
    private String profileName;
    private String profileLastName;
    private String companionName;
    private String companionLastName;
    private LocalDateTime localDateTime;

    public ConversationDto(Integer conversationId, Integer companionId, String profileName, String profileLastName, String companionName, String companionLastName, LocalDateTime localDateTime) {
        this.conversationId = conversationId;
        this.companionId = companionId;
        this.profileName = profileName;
        this.profileLastName = profileLastName;
        this.companionName = companionName;
        this.companionLastName = companionLastName;
        this.localDateTime = localDateTime;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public Integer getCompanionId() {
        return companionId;
    }

    public void setCompanionId(Integer companionId) {
        this.companionId = companionId;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
