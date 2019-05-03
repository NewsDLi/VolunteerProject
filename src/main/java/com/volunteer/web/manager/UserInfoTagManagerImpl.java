package com.volunteer.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volunteer.constant.CommonConstant;
import com.volunteer.model.UserInfoTag;
import com.volunteer.model.UserInfoTagExample;
import com.volunteer.web.dao.UserInfoTagMapper;

@Service
public class UserInfoTagManagerImpl implements UserInfoTagManager{

	@Autowired
	private UserInfoTagMapper userInfoTagMapper;
	
	@Override
	public int insertUserInfoTag(UserInfoTag infoTag) {
		return userInfoTagMapper.insert(infoTag);
	}

	@Override
	public List<UserInfoTag> getUserCareer(Long id) {
		UserInfoTagExample example = new UserInfoTagExample();
		example.createCriteria().andUserIdEqualTo(id);
		example.createCriteria().andTypeBetween(1, 2);
		return userInfoTagMapper.selectByExample(example);
	}

	@Override
	public List<UserInfoTag> getMyHoner(Long id) {
		UserInfoTagExample example = new UserInfoTagExample();
		example.createCriteria().andTypeEqualTo(CommonConstant.TYPE_MEDAL_WALL);
		return userInfoTagMapper.selectByExample(example);
	}

}
