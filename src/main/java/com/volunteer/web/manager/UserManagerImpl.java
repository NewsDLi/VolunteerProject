package com.volunteer.web.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volunteer.model.User;
import com.volunteer.web.dao.Usermapper;

@Service
public class UserManagerImpl implements UserManager{

	@Autowired
	private Usermapper usermapper;
	
	@Override
	public User getUserInfoByUsername(String name){
		User selectUserByName = usermapper.selectUserByName(name);
		return selectUserByName;
	}
}
