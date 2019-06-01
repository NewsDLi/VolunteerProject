package com.volunteer.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.volunteer.constant.CommonConstant;
import com.volunteer.constant.UserConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoCommand;
import com.volunteer.model.UserInfoTag;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
import com.volunteer.web.manager.UserInfoManager;
import com.volunteer.web.manager.UserInfoTagManager;

@Controller
public class PowerController {

	@Autowired
	private UserInfoManager userInfoManager;
	
	@Autowired
	private UserInfoTagManager userInfoTagManager;
	
	@ResponseBody
	@RequestMapping(value = "/power/info", method = RequestMethod.GET)
	public ApiResponse<Object> getInfo(Integer group){
		List<UserInfo> userList = userInfoManager.getUsers(group);
		List<UserInfoCommand> userCommandList = new ArrayList<UserInfoCommand>(userList.size());
		if (userList.isEmpty()){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		for (int i=0;i<userList.size();i++) {
			// id
			UserInfoCommand command = new UserInfoCommand();
			command.setId(userList.get(i).getId());
			// age
			if(userList.get(i).getIdCard().length() == 18){
				String bronYear = userList.get(i).getIdCard().substring(6, 10);
				SimpleDateFormat df = new SimpleDateFormat("yyyy");
	            String year=df.format(new Date());
	            command.setAge(Integer.parseInt(year)-Integer.parseInt(bronYear));
			}else {
				command.setAge(18);
			}
			// 用户生涯信息
			List<UserInfoTag> list = userInfoTagManager.getUserCareer(userList.get(i).getId());
			Integer count = 0;
			for (UserInfoTag userInfoTag : list) {
				if(userInfoTag.getType().equals(CommonConstant.TYPE_CLASS)){
					count += userInfoTag.getTagCount();
					continue;
				}
			}
			// 总期数
			command.setCount(count);
			command.setLoginPhone(userList.get(i).getLoginPhone());
			command.setName(userList.get(i).getName());
			userCommandList.add(command);
		}
		
		return ApiResponse.build(ResponseStatus.SUCCESS, userCommandList);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/permission", method = RequestMethod.GET)
	public ApiResponse<Object> updateInfo(
			HttpServletRequest request,
			String name, 
			Long id, 
			String value, 
			Integer group){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		
		boolean isTrue = false;
		if (userInfo.getRoleId().equals(UserConstant.GUAN_LI_YUAN)){
			isTrue = true;
		}
		if (userInfo.getRoleId().equals(UserConstant.SUPER_GUAN_LI_YUAN)) {
			isTrue = true;
		}
		if(!isTrue){
			return ApiResponse.build(ResponseStatus.PERMISSION, null);
		}
		UserInfo update = new UserInfo();
		update.setId(id);
		// 组长
		if ("groupleader".equals(value)){
			update.setGroupTeam(group);
			update.setIsGroupLeader(true);
		} else if ("professor".equals(value)){ // 教授
			update.setRoleId(UserConstant.JIAO_SHOU);
		} else if ("manager".equals(value)) {
			if (!userInfo.getRoleId().equals(UserConstant.SUPER_GUAN_LI_YUAN)){
				return ApiResponse.build(ResponseStatus.PERMISSION, null);
			}
			// 管理员
			update.setRoleId(UserConstant.GUAN_LI_YUAN);
		}
		boolean result = userInfoManager.updateUserInfoById(update);
		if(result){
			return ApiResponse.build(ResponseStatus.SUCCESS, null);
		}
		return ApiResponse.build(ResponseStatus.FAIL, null);
	}
}
