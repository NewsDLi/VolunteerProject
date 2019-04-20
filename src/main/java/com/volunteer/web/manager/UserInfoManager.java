package com.volunteer.web.manager;

import com.volunteer.model.UserInfo;

public interface UserInfoManager {
	/**
	 *通过openid查询用户信息
	 */
	UserInfo getUserInfoByOpenId(String openId);

	/**
	 *
	 */
	UserInfo getUserInfoByMobile(String mobile);
}
