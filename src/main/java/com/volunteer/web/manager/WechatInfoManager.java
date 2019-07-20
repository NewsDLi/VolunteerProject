package com.volunteer.web.manager;

import com.volunteer.model.UserInfo;
import com.volunteer.model.WechatInfo;

import java.util.List;

public interface WechatInfoManager {
	/**
	 *新增微信用户
	 */
	WechatInfo saveWechatInfo(WechatInfo wechatInfo);

	/**
	 *查询微信用户
	 */
	List<WechatInfo> findWechatInfo(WechatInfo wechatInfo);

	/**
	 * 获取用户相关的“微信”信息
	 * @param userInfo 
	 * @return
	 */
	WechatInfo getWechartInfoById(UserInfo userInfo);

	/**
	 * 更新用户微信信息
	 * @param wechatInfo
	 */
	void updateWechatInfo(WechatInfo wechatInfo);

}
