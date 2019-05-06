package com.volunteer.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.volunteer.constant.CommonConstant;
import com.volunteer.constant.UserConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoExample;
import com.volunteer.model.UserInfoTag;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
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
			if(userInfoTag.getType().equals(CommonConstant.TYPE_POST)){
				post.add(userInfoTag);
			}
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
	
	/**
	 * 勋章墙
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMyHoner")
	public ApiResponse<Object> getMyHoner(HttpServletRequest request){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		List<UserInfoTag> tags = userInfoTagManager.getMyHoner(userInfo.getId());
		if(null == tags || tags.size() == 0){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		return ApiResponse.build(ResponseStatus.SUCCESS, tags);
	}
	
	/**
	 * 权限管理
	 * @param request
	 * @param kewWords 搜索关键字
	 * @param group 小组
	 * @param role 角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/powerManager", method = RequestMethod.POST)
	public ApiResponse<Object> getMemberInfo(HttpServletRequest request,
			@RequestParam(value="kewWords", required=false, defaultValue="")String kewWords,
			@RequestParam(value="group", required=false, defaultValue="")String group,
			@RequestParam(value="role", required=false, defaultValue="")String role,
			@RequestParam(value="role", required=false)Integer pageNum){
		// 每页10条数据
		int pagesize = 10;
		Integer groupteam = null;
		if(StringUtils.isNotBlank(group)){
			groupteam = Integer.parseInt(group);
		}
		Long roles = null;
		if(StringUtils.isNotBlank(role)){
			// 1代表义工、2代表教授、4代表组长 ps:组长没有在权限表中
			roles = Long.parseLong(role);
		}
		if (null == pageNum){
			pageNum = 1;
		}
		// 数据总数
		int count = userInfoManager.getCount();
		
		// 总页数， 是否有余数，是取结果进1
		Integer page = count/pagesize + (count%pagesize)==0 ? 0 : 1;
		
		List<UserInfo> Infos = userInfoManager.searchInfos(kewWords, groupteam, roles, (pageNum - 1) * pagesize, pagesize);
		
		return ApiResponse.build(ResponseStatus.FAIL, null);
	}
}
