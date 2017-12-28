package ua.com.social.demo.entity.impl;


import ua.com.social.demo.entity.DomainObject;

import java.sql.Blob;

public class Photo implements DomainObject {
    private int photoId;
    private String photoName;
    private String photoDescription;
    private Blob photoData;
    private int albumId;
    private int avatar;

    public Photo(int photoId, String photoName, String photoDescription, Blob photoData, int albumId, int avatar) {
        this.photoId = photoId;
        this.photoName = photoName;
        this.photoDescription = photoDescription;
        this.photoData = photoData;
        this.albumId = albumId;
        this.avatar = avatar;
    }

    public Photo() {
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public Blob getPhotoData() {
        return photoData;
    }

    public void setPhotoData(Blob photoData) {
        this.photoData = photoData;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }
}
