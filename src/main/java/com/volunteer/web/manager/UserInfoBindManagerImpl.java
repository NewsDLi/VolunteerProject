package com.volunteer.web.manager;

import com.volunteer.model.UserInfoBindExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.volunteer.model.UserInfoBind;
import com.volunteer.web.dao.UserInfoBindMapper;

import java.util.List;

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

	@Override
	public List<UserInfoBind> selectUserInfoBind(Long wechatId) {
		UserInfoBindExample userInfoBindExample = new UserInfoBindExample();
		userInfoBindExample.createCriteria().andWechatIdEqualTo(wechatId);
		List<UserInfoBind> userInfoBinds = userInfoBindMapper.selectByExample(userInfoBindExample);
		return userInfoBinds;
	}
}
