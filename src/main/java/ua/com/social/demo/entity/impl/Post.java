package ua.com.social.demo.entity.impl;

import ua.com.social.demo.entity.DomainObject;

import java.time.LocalDateTime;

public class Post implements DomainObject {

    private Integer postId;
    private LocalDateTime postDate;
    private Integer wallId;
    private Integer postCreaterId;
    private String postContent;
    private String audio;
    private String video;
    private String photo;

    public Post(Integer postId, LocalDateTime postDate, Integer wallId, Integer postCreaterId, String postContent, String audio, String video, String photo) {
        this.postId = postId;
        this.postDate = postDate;
        this.wallId = wallId;
        this.postCreaterId = postCreaterId;
        this.postContent = postContent;
        this.audio = audio;
        this.video = video;
        this.photo = photo;
    }

    public Post() {
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = LocalDateTime.parse(postDate);
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public Integer getWallId() {
        return wallId;
    }

    public void setWallId(Integer wallId) {
        this.wallId = wallId;
    }

    public Integer getPostCreaterId() {
        return postCreaterId;
    }

    public void setPostCreaterId(Integer postCreaterId) {
        this.postCreaterId = postCreaterId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
