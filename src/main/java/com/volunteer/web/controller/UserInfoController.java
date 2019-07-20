package com.volunteer.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.volunteer.constant.CommonConstant;
import com.volunteer.constant.UserConstant;
import com.volunteer.constant.WxLoginConstant;
import com.volunteer.model.PageInfoCommand;
import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoTag;
import com.volunteer.model.WechatInfo;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
import com.volunteer.utils.AESUtil;
import com.volunteer.utils.ImageUtils;
import com.volunteer.web.manager.UserInfoManager;
import com.volunteer.web.manager.UserInfoTagManager;
import com.volunteer.web.manager.WechatInfoManager;

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
	
	@Autowired
	private WechatInfoManager wechatInfoManager;
	
	/**
	 * 获取用户信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/getUserInfo")
	public String getUserInfo(HttpServletRequest request, Model model){
		// 用户信息
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		WechatInfo wechatInfo = (WechatInfo)request.getSession().getAttribute(WxLoginConstant.WECHAT_USERINFO_SESSION);
		LOGGER.info("session中用户信息为：{}", JSON.toJSONString(userInfo));
		String encryptionIdCard = userInfo.getIdCard();
		String idCard = AESUtil.AESDncode(AESUtil.KEY, encryptionIdCard);
		if(idCard.length() == 18){
			String bronYear = idCard.substring(6, 10);
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year=df.format(new Date());
            userInfo.setAge(Integer.parseInt(year)-Integer.parseInt(bronYear));
		}
		if (StringUtils.isBlank(userInfo.getWorker())){
			userInfo.setWorker("暂无");
		}
		UserInfo userInfo1 = new UserInfo();
		BeanUtils.copyProperties(userInfo, userInfo1);
		userInfo1.setIdCard(idCard);
		model.addAttribute("userInfo", userInfo1);
		model.addAttribute("nickName", wechatInfo.getNickName());
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
			@RequestParam(value="kewWords", required=false)String kewWords,
			@RequestParam(value="group", required=false, defaultValue="")String group,
			@RequestParam(value="role", required=false, defaultValue="")String role,
			@RequestParam(value="pageNum", required=false)String pageNum){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		
		if(userInfo.getRoleId().equals(UserConstant.YI_GONG) && null == userInfo.getGroupTeam()){
			return ApiResponse.build(ResponseStatus.PERMISSION, null);
		}
		
		String header = request.getHeader("referer");
		// 每页10条数据
		int pagesize = 10;
		Integer groupteam = null;
		Integer pagenumber = 1;
		
		// 设置是否首次请求 或者页面是否刷新
		if(header.indexOf("/admin")>0 && StringUtils.isBlank(group) && userInfo.getRoleId().equals(UserConstant.YI_GONG)){
			groupteam = userInfo.getGroupTeam();
		}
		if(StringUtils.isNotBlank(group)){
			// 权限是普通义工的话只能查看本组
			if (userInfo.getRoleId().equals(UserConstant.YI_GONG)){
				groupteam = userInfo.getGroupTeam();
			}else{
				groupteam = Integer.parseInt(group);
			}
		}
		Long roles = null;
		if(StringUtils.isNotBlank(role)){
			// 1代表义工、2代表教授、4代表组长 ps:组长没有在权限表中
			roles = Long.parseLong(role);
		}
		if (StringUtils.isNotBlank(pageNum)){
			pagenumber = Integer.valueOf(pageNum);
		}
		if(StringUtils.isBlank(kewWords)){
			kewWords = null;
		}
		
		List<Integer> groupList = new ArrayList<>();
		// 判断此用户是否具有查看别的组的权限
		if(userInfo.getRoleId().equals(UserConstant.JIAO_SHOU) || userInfo.getRoleId().equals(UserConstant.GUAN_LI_YUAN) || userInfo.getRoleId().equals(UserConstant.SUPER_GUAN_LI_YUAN)){
			groupList = userInfoManager.getAllGroups();
		} else {
			groupList.add(userInfo.getGroupTeam());
		}
		// 数据总数
		int count = userInfoManager.getCount(kewWords, groupteam, roles);
		
		// 总页数， 是否有余数，是取结果进1
		Integer page = count/pagesize + ((count%pagesize)==0 ? 0 : 1);
		
		List<UserInfo> infos = userInfoManager.searchInfos(kewWords, groupteam, roles, (pagenumber - 1) * pagesize, pagesize);
		
		PageInfoCommand<UserInfo> command = new PageInfoCommand<>();
		command.setGroups(groupList);
		command.setPageCount(page);
		command.setInfos(infos);
		command.setPageNum(Integer.parseInt(pageNum));
		return ApiResponse.build(ResponseStatus.SUCCESS, command);
	}
	
	/**
	 * 获取用户信息
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getOtherUserInfo",method = RequestMethod.GET)
	public String getOtherUserInfo(HttpServletRequest request,
			@RequestParam(value="id", required=true)Long id, Model model){
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		// 用户基本信息
		UserInfo userInfo = userInfoManager.getUserInfoById(id);
		
		boolean isCanUpdate = false;
		if ((loginUserInfo.getGroupTeam() != null && loginUserInfo.getGroupTeam().equals(userInfo.getGroupTeam()) && loginUserInfo.getIsGroupLeader()) || loginUserInfo.getRoleId().equals(UserConstant.GUAN_LI_YUAN) || loginUserInfo.getRoleId().equals(UserConstant.SUPER_GUAN_LI_YUAN)){
			isCanUpdate = true;
		}
		
		String idCard = AESUtil.AESDncode(AESUtil.KEY, userInfo.getIdCard());
		if(idCard.length() == 18){
			String bronYear = idCard.substring(6, 10);
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year=df.format(new Date());
            userInfo.setAge(Integer.parseInt(year)-Integer.parseInt(bronYear));
		}
		if (StringUtils.isBlank(userInfo.getWorker())){
			userInfo.setWorker("暂无");
		}
		
		
		// 用户生涯信息
		List<UserInfoTag> list = userInfoTagManager.getUserCareer(id);
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
		// 判断是否为管理员
		if (loginUserInfo.getRoleId().equals(UserConstant.GUAN_LI_YUAN) || loginUserInfo.getRoleId().equals(UserConstant.SUPER_GUAN_LI_YUAN)){
			model.addAttribute("showHoner", true);
		}
		model.addAttribute("havingClass", havingClass);
		model.addAttribute("post", post);
		model.addAttribute("count", count);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("isCanUpdate", isCanUpdate);
		return "showInfoMeaasge";
	}
	
	/**
	 * 组长或者管理员更新用户信息
	 * @param request
	 * @param userInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateUserInfo")
	public ApiResponse<Boolean> updateUserInfo(HttpServletRequest request, UserInfo userInfo){
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		boolean isCanUpdate = false;
		if (loginUserInfo.getIsGroupLeader()){
			isCanUpdate = true;
		}
		if (loginUserInfo.getRoleId().equals(UserConstant.GUAN_LI_YUAN) || loginUserInfo.getRoleId().equals(UserConstant.SUPER_GUAN_LI_YUAN)){
			isCanUpdate = true;
		}
		if (!isCanUpdate){
			return ApiResponse.build(ResponseStatus.FAIL, false);
		}
		UserInfo beforeUpdatePersonInfo = userInfoManager.getUserInfoById(userInfo.getId());
		if ((loginUserInfo.getGroupTeam().equals(beforeUpdatePersonInfo.getGroupTeam()) && loginUserInfo.getIsGroupLeader()) || loginUserInfo.getRoleId().equals(UserConstant.GUAN_LI_YUAN) || loginUserInfo.getRoleId().equals(UserConstant.SUPER_GUAN_LI_YUAN)){
			userInfo.setVersion(new Date());
			userInfo.setUpdateBy(loginUserInfo.getId());
			boolean updateUserInfoById = userInfoManager.updateUserInfoById(userInfo);
			if (updateUserInfoById){
				return ApiResponse.build(ResponseStatus.SUCCESS, true);
			}
		}
		return ApiResponse.build(ResponseStatus.FAIL, false);
	}
	
	
	/**
	 * 用户自己更新信息
	 * @param request
	 * @param userInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateBaseUserInfo")
	public ApiResponse<Object> updateBaseUserInfo(HttpServletRequest request, UserInfo userInfo, String nickName){
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		userInfo.setId(loginUserInfo.getId());
		userInfo.setVersion(new Date());
		userInfo.setUpdateBy(loginUserInfo.getId());
		boolean updateUserInfoById = userInfoManager.updateUserInfoById(userInfo);
		if (!updateUserInfoById){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		// 更新session中的userinfo信息
		UserInfo userInfoById = userInfoManager.getUserInfoById(loginUserInfo.getId());
		request.getSession().setAttribute(UserConstant.LOGIN_PHONE, userInfoById);
		// 更新session中微信的用户信息
		WechatInfo wechatInfo = (WechatInfo) request.getSession().getAttribute(WxLoginConstant.WECHAT_USERINFO_SESSION);
		wechatInfo.setNickName(nickName);
		wechatInfoManager.updateWechatInfo(wechatInfo);
		return ApiResponse.build(ResponseStatus.SUCCESS, true);
	}
	
	@ResponseBody
	@RequestMapping("/addStation")
	public ApiResponse<Object> addStation(HttpServletRequest request,
			@RequestParam(value="userId", required=true)Long userId,
			@RequestParam(value="station", required=true)String station){
		// 判断是否管理员
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		
		boolean isTrue = false;
		if (loginUserInfo.getRoleId().equals(UserConstant.GUAN_LI_YUAN)){
			isTrue = true;
		}
		if (loginUserInfo.getRoleId().equals(UserConstant.SUPER_GUAN_LI_YUAN)) {
			isTrue = true;
		}
		if(!isTrue){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		UserInfoTag userInfoTag = new UserInfoTag();
		userInfoTag.setTagCount(1);
		userInfoTag.setTagName(station);
		userInfoTag.setUserId(userId);
		userInfoTag.setType(CommonConstant.TYPE_POST);
		
		int insertUserInfoTag = userInfoTagManager.insertUserInfoTag(userInfoTag);
		if(insertUserInfoTag !=1){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		return ApiResponse.build(ResponseStatus.SUCCESS, null);
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateRole",method = RequestMethod.GET)
	public ApiResponse<Object> addRole(HttpServletRequest request,
			@RequestParam(value="userId", required=true)Long userId,
			@RequestParam(value="groupLeader", required=true)String groupLeader,
			@RequestParam(value="role", required=true)String role,
			@RequestParam(value="messageboard", required=true)String messageboard){
		// 判断是否管理员
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		
		boolean isTrue = false;
		if (loginUserInfo.getRoleId().equals(UserConstant.GUAN_LI_YUAN)){
			isTrue = true;
		}
		if (loginUserInfo.getRoleId().equals(UserConstant.SUPER_GUAN_LI_YUAN)) {
			isTrue = true;
		}
		if(!isTrue){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		Boolean leader = null;
		Long roleId = null;
		Boolean message = null;
		if(StringUtils.isNotBlank(groupLeader)){
			if("true".equals(groupLeader)){
				leader = true;
			}
			if("false".equals(groupLeader)){
				leader = false;
			}
		}
		if(StringUtils.isNotBlank(messageboard)){
			if("true".equals(messageboard)){
				message = true;
			}
			if("false".equals(messageboard)){
				message = false;
			}
		}
		
		UserInfo updateUserInfo = new UserInfo();
		if (StringUtils.isNotBlank(role)){
			roleId = Long.parseLong(role);
		}
		if (roleId!=null){
			updateUserInfo.setRoleId(roleId);
		}
		if (leader != null){
			updateUserInfo.setIsGroupLeader(leader);
		}
		if (message != null){
			updateUserInfo.setIsMessageBoard(message);
		}
		if (message == null && leader==null && roleId==null){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		updateUserInfo.setId(userId);
		updateUserInfo.setVersion(new Date());
		updateUserInfo.setUpdateBy(loginUserInfo.getId());
		boolean updateUserInfoById = userInfoManager.updateUserInfoById(updateUserInfo);
		if (!updateUserInfoById){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		return ApiResponse.build(ResponseStatus.SUCCESS, true);
	}
	
	@ResponseBody
	@RequestMapping(value = "/removeUser",method = RequestMethod.GET)
	public ApiResponse<Object> removeUser(HttpServletRequest request,
			@RequestParam(value="userId", required=true)Long userId){
		// 判断是否管理员,自己不能删除自己
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		boolean isTrue = false;
		if (loginUserInfo.getRoleId().equals(UserConstant.GUAN_LI_YUAN)){
			isTrue = true;
		}
		if (loginUserInfo.getRoleId().equals(UserConstant.SUPER_GUAN_LI_YUAN)) {
			isTrue = true;
		}
		//
		if (userId.equals(loginUserInfo.getId())) {
			isTrue = false;
		}
		if(!isTrue){
			return ApiResponse.build(ResponseStatus.PERMISSION, null);
		}
		// 等级相同无法删除
		UserInfo userInfoById = userInfoManager.getUserInfoById(userId);
		if (userInfoById.getRoleId().equals(loginUserInfo.getRoleId())){
			return ApiResponse.build(ResponseStatus.PERMISSION, null);
		}
		if (userInfoById.getRoleId().equals(UserConstant.SUPER_GUAN_LI_YUAN) ){
			return ApiResponse.build(ResponseStatus.PERMISSION, null);
		}
		UserInfo updateUser = new UserInfo();
		updateUser.setLifecycle(0);
		updateUser.setId(userId);
		boolean updateUserInfoById = userInfoManager.updateUserInfoById(updateUser);
		if (!updateUserInfoById){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		return ApiResponse.build(ResponseStatus.SUCCESS, null);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllGroups",method = RequestMethod.GET)
	public ApiResponse<Object> getAllGroups(){
		try{
			List<Integer> allGroups = userInfoManager.getAllGroups();
			return ApiResponse.build(ResponseStatus.SUCCESS, allGroups);
		}catch(Exception e){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
	}
	
	@RequestMapping(value="/uploadUserImg", method = {RequestMethod.POST, RequestMethod.GET})
	public String uploadUserImg(HttpServletRequest request, MultipartFile userImg){
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		String originalFilename = userImg.getOriginalFilename();
		if(StringUtils.isNotBlank(originalFilename)){
			String url = savePic(userImg, request);
			loginUserInfo.setUserPic(url);
		}
		boolean result = userInfoManager.updateUserInfoById(loginUserInfo);
		if(result) {
			request.getSession().setAttribute(UserConstant.LOGIN_PHONE, loginUserInfo);
		}
		return "redirect:/getUserInfo";
	}
	
	private String savePic(MultipartFile lightImg, HttpServletRequest request){
		String originalFilename = lightImg.getOriginalFilename();
		//上传图片
		String realPath = CommonConstant.UPLOAD_PIC_URL+"/images/userpic/";
		if(lightImg!=null && originalFilename!=null && originalFilename.length()>0){
			String url = ImageUtils.saveImage(request, lightImg, realPath);
			return url;
		}
		return null;
	}
	
}
