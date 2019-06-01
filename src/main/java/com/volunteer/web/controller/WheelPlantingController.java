package com.volunteer.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.volunteer.constant.UserConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.model.WhellPlanting;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
import com.volunteer.utils.TimeUtil;
import com.volunteer.web.manager.WheelPlantingManager;

/**
 * @author NewsDLee
 * @desc 首页轮播管理
 */
@Controller
public class WheelPlantingController {

	@Autowired
	private WheelPlantingManager wheelPlantingManager;
	
	@RequestMapping("/getWheelPlanting")
	public String getWheelPlanting(){
		return "wheelPlanting";
	}
	
	@ResponseBody
	@RequestMapping("/queryAll")
	public ApiResponse<Object> queryAll(HttpServletRequest request){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		boolean isTrue = false;
		if (userInfo.getRoleId().equals(UserConstant.GUAN_LI_YUAN)){
			isTrue = true;
		}
		if (userInfo.getRoleId().equals(UserConstant.SUPER_GUAN_LI_YUAN)) {
			isTrue = true;
		}
		if(!isTrue){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		List<WhellPlanting> list = wheelPlantingManager.queryAll();
		for (WhellPlanting whellPlanting : list) {
			whellPlanting.setTime(TimeUtil.formatDate(whellPlanting.getVersion(), "yyyy-MM-dd HH:mm:ss"));
		}
		return ApiResponse.build(ResponseStatus.SUCCESS, list);
	}
	
	@RequestMapping("/getWhellPlantingById")
	public String getWhellPlantingById(HttpServletRequest request, Model model,
			@RequestParam(value="id", required=true)Long id){
		WhellPlanting whellPlanting = wheelPlantingManager.queryById(id);
		model.addAttribute("whellPlanting", whellPlanting);
		return "UpdateWhellPlanting";
	}
}
