package com.volunteer.web.dao;

import com.volunteer.model.UserInfo;
import com.volunteer.model.WechatInfo;
import org.springframework.stereotype.Repository;

/**
 * @author yuan
 * @date 2019年4月15日15:08:52
 * @desc 查询用户信息
 */
@Repository
public interface WechatInfoMapper {

	/**
	 * 通过openId查询用户信息
	 */
	Boolean saveWechatInfo(WechatInfo wechatInfo);

}
