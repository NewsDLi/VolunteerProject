package com.volunteer.web.manager;

import com.volunteer.common.WechatMessage;
import com.volunteer.model.WechatInfo;

import java.util.List;

public interface WechatInfoManager {
	/**
	 *新增微信用户
	 */
	WechatInfo  saveWechatInfo(WechatInfo wechatInfo);

	/**
	 *查询微信用户
	 */
	List<WechatInfo> findWechatInfo(WechatInfo wechatInfo);

}
