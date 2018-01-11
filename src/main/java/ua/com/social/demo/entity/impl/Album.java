package ua.com.social.demo.entity.impl;

import ua.com.social.demo.entity.DomainObject;

public class Album implements DomainObject {
    private Integer albumId;
    private String albumName;
    private Integer profileId;

    public Album(String albumName, Integer profileId) {
        this.albumName = albumName;
        this.profileId = profileId;
    }

    public Album() {

    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
