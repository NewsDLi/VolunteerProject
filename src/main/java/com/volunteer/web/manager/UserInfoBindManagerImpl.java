package com.volunteer.web.manager;

import com.volunteer.model.UserInfoBind;
import com.volunteer.model.WechatInfo;
import com.volunteer.web.dao.UserInfoBindMapper;
import com.volunteer.web.dao.WechatInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoBindManagerImpl implements UserInfoBindManager{

	@Autowired
	private UserInfoBindMapper userInfoBindMapper;

	@Override
	@Transactional
	public Long saveUserInfoBind(UserInfoBind userInfoBind) {
		Long aLong = null;
		try {
			aLong = userInfoBindMapper.saveUserInfoBind(userInfoBind);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aLong;
	}
}
