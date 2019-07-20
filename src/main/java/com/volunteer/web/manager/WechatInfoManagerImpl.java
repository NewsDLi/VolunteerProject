package com.volunteer.web.manager;

import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoBind;
import com.volunteer.model.WechatInfo;
import com.volunteer.model.WechatInfoExample;
import com.volunteer.web.dao.WechatInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class WechatInfoManagerImpl implements WechatInfoManager{

	@Autowired
	private WechatInfoMapper wechatInfoMapper;
	
	@Autowired
	private UserInfoBindManager userInfoBindManager;

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

	@Transactional
	@Override
	public WechatInfo getWechartInfoById(UserInfo userInfo) {
		WechatInfo info = wechatInfoMapper.getWechartInfoByUserId(userInfo.getId());
		if (null ==info){
			info = new WechatInfo();
			info.setNickName(userInfo.getName());
			info.setOpenId(UUID.randomUUID().toString());
			info.setSex(userInfo.getSex());
			// 设置默认头像
			info.setImage("/images/head/testhead.jpg");
			wechatInfoMapper.insert(info);
			UserInfoBind userInfoBind = new UserInfoBind();
            userInfoBind.setUserId(userInfo.getId());
            userInfoBind.setWechatId(info.getId());
            userInfoBindManager.saveUserInfoBind(userInfoBind);
		}
		return info;
	}

	@Override
	public void updateWechatInfo(WechatInfo wechatInfo) {
		wechatInfoMapper.updateByPrimaryKeySelective(wechatInfo);
	}

}
