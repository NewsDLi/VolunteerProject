package com.volunteer.model;

import java.io.Serializable;

public class UserInfoBind  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4296053434749983628L;

	private Long id;

    private Long userId;

    private Long wechatId;

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

    public Long getWechatId() {
        return wechatId;
    }

    public void setWechatId(Long wechatId) {
        this.wechatId = wechatId;
    }
}