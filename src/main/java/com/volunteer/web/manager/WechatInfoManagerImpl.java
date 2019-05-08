package com.volunteer.web.manager;

import com.volunteer.common.WechatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.volunteer.model.WechatInfo;
import com.volunteer.web.dao.WechatInfoMapper;

@Service
public class WechatInfoManagerImpl implements WechatInfoManager{

	@Autowired
	private WechatInfoMapper wechatInfoMapper;

	@Override
	@Transactional
	public Integer saveWechatInfo(WechatMessage wechatMessage) {
		Integer integer = null;
		try {
			WechatInfo wechatInfo = new WechatInfo();
			wechatInfo.setOpenId(wechatMessage.getOpenId());
			wechatInfo.setNickName(wechatMessage.getNickname());
			wechatInfo.setSex(wechatMessage.getSex());
			wechatInfo.setProvince(wechatMessage.getProvince());
			wechatInfo.setCountry(wechatMessage.getCountry());
			wechatInfo.setCity(wechatMessage.getCity());
			integer = wechatInfoMapper.insert(wechatInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return integer;
	}
}
