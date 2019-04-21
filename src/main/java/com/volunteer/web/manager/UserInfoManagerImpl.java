package com.volunteer.web.manager;

import com.volunteer.common.UserInfoBindCommand;
import com.volunteer.model.UserInfo;
import com.volunteer.web.dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoManagerImpl implements UserInfoManager{

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfoBindCommand getUserInfoByOpenId(String openId) {
		return userInfoMapper.selectUserInfoByOpenId(openId);
	}

	@Override
	public UserInfo getUserInfoByMobile(String mobile) {
		return userInfoMapper.selectUserInfoByMobile(mobile);
	}
}
