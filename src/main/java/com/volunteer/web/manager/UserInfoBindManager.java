package com.volunteer.web.manager;

import com.volunteer.model.UserInfoBind;

import java.util.List;

public interface UserInfoBindManager {
	/**
	 *新增绑定用户信息
	 */
	Integer  saveUserInfoBind(UserInfoBind userInfoBind);
	/**
	 *查询绑定用户信息
	 */
	List<UserInfoBind> selectUserInfoBind(Long wechatId);

}
