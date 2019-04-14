package com.volunteer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteer.constant.ApiResponse;
import com.volunteer.constant.ResponseStatus;
import com.volunteer.manager.UserManager;
import com.volunteer.model.User;

/**
 * @author NewsDLee
 * @date 2019年4月14日15:08:52
 * @desc userController测试框架是否运行正常
 */
@RestController
public class UserController {

	@Autowired
	private UserManager userManager;
	
	@RequestMapping("/getUser")
	public ApiResponse<User> getUser(HttpServletRequest request, HttpServletResponse response){
		ApiResponse<User> apiResponse = new ApiResponse<>();
		User userInfoByUsername = userManager.getUserInfoByUsername("zhangsan");
		return apiResponse.build(ResponseStatus.SUCCESS_MESSAGE, userInfoByUsername);
	}
}
