package com.volunteer.web.controller.forum.command;

import java.util.Date;

public class CommunityArticlesCommand {
    /**
     * 生命周期初始状态
     */
    public static final Integer START_LIFECYCLE = 1;
    /**
     * 生命周期结束状态
     */
    public static final Integer END_LIFECYCLE = 2;
    /**
     * 论坛类型:义工心得
     */
    public static final Integer TYPE_YIGONGXINDE = 1;
    /**
     * 论坛类型:国学经典
     */
    public static final Integer TYPE_GUOXUEJINGDIAN = 2;
    /**
     * 论坛类型:曲艺杂谈
     */
    public static final Integer TYPE_QUYIZATAN = 3;
    /**
     * 论坛类型:诗词歌赋
     */
    public static final Integer TYPE_SHICIGEFU = 4;

    private Long id;

    private String title;

    private String publicationTime;

    private String subheading;

    private String author;

    private Integer type;

    private Integer lifecycle;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(String publicationTime) {
        this.publicationTime = publicationTime;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading == null ? null : subheading.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Integer lifecycle) {
        this.lifecycle = lifecycle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}