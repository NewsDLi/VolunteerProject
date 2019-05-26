package com.volunteer.web.manager;

import java.util.List;

import com.volunteer.model.UserInfoTag;

public interface UserInfoTagManager {
	
	int insertUserInfoTag(UserInfoTag infoTag);

	List<UserInfoTag> getUserCareer(Long id);

	void tagCountPlusOne(Long id);

	UserInfoTag getUserInfoTag(Long id);
}
