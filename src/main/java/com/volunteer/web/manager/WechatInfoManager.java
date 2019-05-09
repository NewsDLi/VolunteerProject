package com.volunteer.web.manager;

import com.volunteer.common.WechatMessage;

public interface WechatInfoManager {
	/**
	 *新增微信用户
	 */
	Integer  saveWechatInfo(WechatMessage wechatMessage);

}
