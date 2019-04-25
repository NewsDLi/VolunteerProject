package com.volunteer.web.dao;

import com.volunteer.model.UserInfoBind;
import com.volunteer.model.WechatInfo;
import org.springframework.stereotype.Repository;

/**
 * @author yuan
 * @date 2019年4月15日15:08:52
 * @desc 用户信息绑定微信用户信息
 */
@Repository
public interface UserInfoBindMapper {

	/**
	 * 通过openId查询用户信息
	 */
	Long  saveUserInfoBind(UserInfoBind userInfoBind);

}
