package com.volunteer.dao;

import org.springframework.stereotype.Repository;

import com.volunteer.model.User;

@Repository
public interface Usermapper {

	User selectUserByName(String name);
}
