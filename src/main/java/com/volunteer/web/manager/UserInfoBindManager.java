package com.volunteer.web.manager;

import com.volunteer.model.UserInfoBind;

public interface UserInfoBindManager {
	/**
	 *新增绑定用户信息
	 */
	Integer  saveUserInfoBind(UserInfoBind userInfoBind);

}
