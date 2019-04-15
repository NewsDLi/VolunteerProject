package com.volunteer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteer.manager.UserManager;
import com.volunteer.model.PropBean;
import com.volunteer.model.User;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;

/**
 * @author NewsDLee
 * @date 2019年4月14日15:08:52
 * @desc userController测试框架是否运行正常
 */
@RestController
public class UserController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private PropBean pro;
	
	@RequestMapping("/getUser")
	public ApiResponse<User> getUser(HttpServletRequest request, HttpServletResponse response){
		User userInfoByUsername = userManager.getUserInfoByUsername("zhangsan");
		userInfoByUsername.setUsername(pro.getName());
		return ApiResponse.build(ResponseStatus.SUCCESS, userInfoByUsername);
	}
}
