package com.volunteer.web.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.volunteer.model.PageNation;
import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoExample;
import com.volunteer.web.dao.UserInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volunteer.common.UserInfoBindCommand;

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
//		return userInfoMapper.checkUserIsExist(idCard);
		return  null;
	}

	@Override
	public Integer updateUserIfoToDescption(Long id, String descption,String hobby) {
		UserInfoExample userInfoExample = new UserInfoExample();
		userInfoExample.createCriteria().andIdCardEqualTo(id.toString());
		UserInfo userInfo = new UserInfo();
		if(!StringUtils.isBlank(descption)){
			userInfo.setDescption(descption);
		}
		if(!StringUtils.isBlank(hobby)){
			userInfo.setHobby(hobby);
		}
		userInfo.setDescption(descption);
		int exampleSelective = userInfoMapper.updateByExampleSelective(userInfo, userInfoExample);
		return exampleSelective;
	}

	@Override
	public UserInfo selectByPrimaryKey(Long id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int getCount() {
		return userInfoMapper.getCount();
	}

	@Override
	public List<UserInfo> searchInfos(String kewWords, Integer groupteam, Long roles, int begin,int pageSize) {
		PageNation pageNation = new PageNation(kewWords, groupteam, roles, begin, pageSize);
		return userInfoMapper.searchInfos(pageNation);
	}
}
