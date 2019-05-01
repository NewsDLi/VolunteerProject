package com.volunteer.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.volunteer.constant.CommonConstant;
import com.volunteer.constant.UserConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoExample;
import com.volunteer.model.UserInfoTag;
import com.volunteer.web.manager.UserInfoManager;
import com.volunteer.web.manager.UserInfoTagManager;

/**
 * @author NewsDLee
 * @date 2019年4月22日22:12:20
 * @desc 个人信息相关
 */
@Controller
public class UserInfoController {

	private Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	private UserInfoManager userInfoManager;
	
	@Autowired
	private UserInfoTagManager userInfoTagManager;
	
	@RequestMapping("/getUserInfo")
	public String getUserInfo(HttpServletRequest request, Model model){
		// 用户信息
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		String idCard = userInfo.getIdCard();
		if(idCard.length() == 18){
			String bronYear = idCard.substring(6, 10);
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year=df.format(new Date());
            userInfo.setAge(Integer.parseInt(year)-Integer.parseInt(bronYear));
		}
		if (StringUtils.isBlank(userInfo.getWorker())){
			userInfo.setWorker("暂无");
		}
		model.addAttribute("userInfo", userInfo);
		return "myinfo";
	}
	
	/**
	 * 义工生涯
	 * @return
	 */
	@RequestMapping("/getUserCareer")
	public String getUserCareer(HttpServletRequest request, Model model){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		List<UserInfoTag> list = userInfoTagManager.getUserCareer(userInfo.getId());
		// 上过的课程
		List<UserInfoTag> havingClass = new ArrayList<UserInfoTag>();
		// 义工岗位
		List<UserInfoTag> post = new ArrayList<UserInfoTag>();
		Integer count = 0;
		for (UserInfoTag userInfoTag : list) {
			if(userInfoTag.getType().equals(CommonConstant.TYPE_CLASS)){
				count += userInfoTag.getTagCount();
				havingClass.add(userInfoTag);
				continue;
			}
			post.add(userInfoTag);
		}
		model.addAttribute("havingClass", havingClass);
		model.addAttribute("post", post);
		model.addAttribute("count", count);
		return "mycareer";
	}
	
	
	@RequestMapping("/userLogin/Demo.htm")
	public String userLogin(HttpServletRequest request, Long id){
		UserInfo userInfo = userInfoManager.selectByPrimaryKey(id);
		request.getSession().setAttribute(UserConstant.LOGIN_PHONE, userInfo);
		return "redirect:/mypage";
	}
}
