package com.volunteer.web.manager;

import com.volunteer.common.UserInfoBindCommand;
import com.volunteer.model.UserInfo;

import java.util.List;

public interface UserInfoManager {
	/**
	 *通过openid查询用户信息
	 */
	UserInfoBindCommand getUserInfoByOpenId(String openId);

	/**
	 *
	 */
	List<UserInfo> getUserInfoByMobile(String mobile);

	/**
	 * 插入用户信息，并返回主键
	 * @param user
	 * @return
	 */
	Long insertUserInfo(UserInfo user);

	/**
	 * 通过身份证号，查询用户是否存在
	 * @param idCard
	 * @return
	 */
	Integer checkUserIsExist(String idCard);

}
