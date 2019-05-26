package com.volunteer.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.volunteer.constant.UserConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoTag;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
import com.volunteer.web.manager.HonerManager;
import com.volunteer.web.manager.UserInfoManager;
import com.volunteer.web.manager.UserInfoTagManager;

@Controller
public class UserInfoTagController {

	@Autowired
	private UserInfoTagManager userInfoTagManager;
	
	@Autowired
	private HonerManager honerManager;
	
	@ResponseBody
	@RequestMapping(value="/plusOne",method = RequestMethod.GET)
	public ApiResponse<Object> plusOne(HttpServletRequest request,
			@RequestParam(value="id", required=true)Long id){
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		if (loginUserInfo.getRoleId() != 3L) {
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		userInfoTagManager.tagCountPlusOne(id);
		UserInfoTag userInfoTag = userInfoTagManager.getUserInfoTag(id);
		honerManager.updateUserHonerInfo(userInfoTag.getUserId());
		return ApiResponse.build(ResponseStatus.SUCCESS, null);
	}
}
