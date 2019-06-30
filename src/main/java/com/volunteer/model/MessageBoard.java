package com.volunteer.model;

import java.io.Serializable;
import java.util.Date;

public class MessageBoard  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2075049140331048321L;

	private Long id;

    private Long userId;

    private String content;

    private Integer lifecycle;

    private Date version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Integer lifecycle) {
        this.lifecycle = lifecycle;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }
}