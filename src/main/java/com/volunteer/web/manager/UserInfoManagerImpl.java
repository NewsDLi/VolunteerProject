package com.volunteer.web.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volunteer.common.UserInfoBindCommand;
import com.volunteer.model.UserInfo;
import com.volunteer.web.dao.UserInfoMapper;

@Service
public class UserInfoManagerImpl implements UserInfoManager{

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfoBindCommand getUserInfoByOpenId(String openId) {
		return null;
	}

	@Override
	public UserInfo getUserInfoByMobile(String mobile) {
		return null;
	}

	@Override
	public Long insertUserInfo(UserInfo user) {
		int insert = userInfoMapper.insert(user);
		if(insert != 1){
			return null;
		}
		return user.getId();
	}

	@Override
	public Integer checkUserIsExist(String idCard) {
		return userInfoMapper.checkUserIsExist(idCard);
	}
}
