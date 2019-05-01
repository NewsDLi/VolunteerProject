package com.volunteer.web.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.volunteer.model.UserInfoBind;
import com.volunteer.web.dao.UserInfoBindMapper;

@Service
public class UserInfoBindManagerImpl implements UserInfoBindManager{

	@Autowired
	private UserInfoBindMapper userInfoBindMapper;

	@Override
	@Transactional
	public Integer saveUserInfoBind(UserInfoBind userInfoBind) {
		Integer insert = null;
		try {
			insert = userInfoBindMapper.insert(userInfoBind);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insert;
	}
}
