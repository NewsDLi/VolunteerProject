package com.volunteer.web.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volunteer.model.UserInfoTag;
import com.volunteer.web.dao.UserInfoTagMapper;

@Service
public class UserInfoTagManagerImpl implements UserInfoTagManager{

	@Autowired
	private UserInfoTagMapper userInfoTagMapper;
	
	@Override
	public int insertUserInfoTag(UserInfoTag infoTag) {
		return userInfoTagMapper.insert(infoTag);
	}

}
