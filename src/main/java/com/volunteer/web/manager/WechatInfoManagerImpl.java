package com.volunteer.web.manager;

import com.volunteer.model.WechatInfo;
import com.volunteer.web.dao.UserInfoMapper;
import com.volunteer.web.dao.WechatInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WechatInfoManagerImpl implements WechatInfoManager{

	@Autowired
	private WechatInfoMapper wechatInfoMapper;

	@Override
	@Transactional
	public Long  saveWechatInfo(WechatInfo wechatInfo) {
		Long integer = null;
		try {
			 integer = wechatInfoMapper.saveWechatInfo(wechatInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return integer;
	}
}
