package com.volunteer.web.manager;

import com.volunteer.model.UserInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volunteer.common.UserInfoBindCommand;
import com.volunteer.model.UserInfo;
import com.volunteer.web.dao.UserInfoMapper;

import java.util.List;

@Service
public class UserInfoManagerImpl implements UserInfoManager{

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfoBindCommand getUserInfoByOpenId(String openId) {
		return null;
	}

	@Override
	public List<UserInfo> getUserInfoByMobile(String mobile) {
		UserInfoExample userInfoExample = new UserInfoExample();
		userInfoExample.createCriteria().andLoginPhoneEqualTo(mobile);
		List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
		return userInfos;
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
