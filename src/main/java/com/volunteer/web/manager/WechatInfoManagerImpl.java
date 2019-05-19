package com.volunteer.web.manager;

import com.volunteer.common.WechatMessage;
import com.volunteer.model.WechatInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.volunteer.model.WechatInfo;
import com.volunteer.web.dao.WechatInfoMapper;

import java.util.List;

@Service
public class WechatInfoManagerImpl implements WechatInfoManager{

	@Autowired
	private WechatInfoMapper wechatInfoMapper;

	@Override
	@Transactional
	public WechatInfo saveWechatInfo(WechatInfo wechatInfo) {
		Integer integer = null;
		try {
			integer = wechatInfoMapper.insertSelective(wechatInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wechatInfo;
	}

	@Override
	public List<WechatInfo> findWechatInfo(WechatInfo wechatInfo) {
		WechatInfoExample wechatInfoExample = new WechatInfoExample();
		wechatInfoExample.createCriteria().andOpenIdEqualTo(wechatInfo.getOpenId());
		List<WechatInfo> wechatInfos = wechatInfoMapper.selectByExample(wechatInfoExample);
		return wechatInfos;
	}
}
