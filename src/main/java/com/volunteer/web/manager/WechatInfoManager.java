package com.volunteer.web.manager;

import com.volunteer.common.UserInfoBindCommand;
import com.volunteer.model.UserInfo;
import com.volunteer.model.WechatInfo;

public interface WechatInfoManager {
	/**
	 *新增微信用户
	 */
	Long  saveWechatInfo(WechatInfo wechatInfo);

}
