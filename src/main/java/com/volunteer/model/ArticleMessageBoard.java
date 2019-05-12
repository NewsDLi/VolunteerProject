package com.volunteer.model;

import java.util.Date;

public class ArticleMessageBoard {
    private Long id;

    private Long communityArticlesId;

    private String message;

    private Date version;

    private Long userId;

    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommunityArticlesId() {
        return communityArticlesId;
    }

    public void setCommunityArticlesId(Long communityArticlesId) {
        this.communityArticlesId = communityArticlesId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}