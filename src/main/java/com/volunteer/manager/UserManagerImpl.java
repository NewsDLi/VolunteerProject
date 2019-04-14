package com.volunteer.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volunteer.dao.Usermapper;
import com.volunteer.model.User;

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
