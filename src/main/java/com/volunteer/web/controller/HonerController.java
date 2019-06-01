package com.volunteer.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.volunteer.constant.UserConstant;
import com.volunteer.model.Honer;
import com.volunteer.model.UserInfo;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
import com.volunteer.utils.ImageUtils;
import com.volunteer.web.manager.HonerManager;
import com.volunteer.web.manager.UserInfoManager;
import com.volunteer.web.manager.UserInfoTagManager;

@Controller
public class HonerController {

	@Autowired
	private HonerManager honerManager;
	
	@Autowired
	private UserInfoManager userInfoManager;
	
	@Autowired
	private UserInfoTagManager userInfoTagManager;
	
	/**
     * 勋章墙
     *
     * @return
     */
    @RequestMapping("/honer.htm")
    public String honer(HttpServletRequest request, Model model) {
    	UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		List<Honer> list = honerManager.getHoner(userInfo.getId());
		List<Object> object = sortListHoner(list);
		if (null == object) {
			return "redirect:/login.json";
		}
		model.addAttribute("myHoner", object);
        return "honer";
    }
	
    /**
     * 管理勋章墙
     * @param request
     * @param model
     * @return
     */
	@RequestMapping("/managerhoner")
	public String toManagerHoner(HttpServletRequest request, Model model){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		boolean isTrue = false;
		if (userInfo.getRoleId().equals(UserConstant.GUAN_LI_YUAN)){
			isTrue = true;
		}
		if (userInfo.getRoleId().equals(UserConstant.SUPER_GUAN_LI_YUAN)) {
			isTrue = true;
		}
		if(!isTrue){
			return "redirect:/login.json";
		}
		List<Honer> allHoner = honerManager.getAllHoner();
		List<Object> sortListHoner = sortListHoner(allHoner);
		if (null == sortListHoner) {
			return "redirect:/login.json";
		}
		model.addAttribute("myHoner", sortListHoner);
		return "managerhoner";
	}
	
	private List<Object> sortListHoner(List<Honer> list) {
		int honerListSize = list.size();
		// 默认设置三列一行
		int size = (honerListSize / 3) + ((honerListSize % 3) == 0 ? 0 : 1);
		if (size == 0){
			return null;
		}
		List<Object> object = new ArrayList<Object>();
		// 分为size组
		List<Honer> honer = null;
		for (int i=1; i<=size; i++) {
			honer = new ArrayList<Honer>(3);
			for(int j=(i-1)*3; j<i*3; j++){
				if (j >= honerListSize){
					break;
				}
				honer.add(list.get(j));
			}
			object.add(honer);
		}
		return object;
	}
	
	@RequestMapping("/toUpdateHoner")
	public String toUpdateHoner(HttpServletRequest request, Model model, Long id) {
		String attribute = (String)request.getSession().getAttribute("showMessage");
		request.getSession().removeAttribute("showMessage");
		if(StringUtils.isNotBlank(attribute)){
			model.addAttribute("showMessage", attribute);
		}
		if (null == id){
			model.addAttribute("add", true);
			return "updateHoner";
		}
		Honer honer = honerManager.getHonerById(id);
		model.addAttribute("honer", honer);
		model.addAttribute("update", true);
		return "updateHoner";
	}
	
	@RequestMapping(value = "/updateHoner", method = {RequestMethod.POST, RequestMethod.GET})
	public String updateHoner(HttpServletRequest request, 
			HttpServletResponse response, 
			Model model,
			Honer honer,
			MultipartFile lightImg,
			MultipartFile grayImg){
		// 不为空表示更新 为空表示新增
		honer.setVersion(new Date());
		if(null == honer.getId()){
			if (StringUtils.isBlank(lightImg.getOriginalFilename()) || StringUtils.isBlank(grayImg.getOriginalFilename())){
				request.getSession().setAttribute("showMessage", "新增，两张图片都不能为空");
				return "redirect:/toUpdateHoner";
			}
			honer.setLight(savePic(lightImg, request));
			honer.setGray(savePic(grayImg, request));
			Long id = honerManager.save(honer);
			request.getSession().setAttribute("showMessage", "添加成功！");
			return "redirect:/toUpdateHoner?id=" + id;
		}
		if(StringUtils.isNotBlank(lightImg.getOriginalFilename())){
			String light = savePic(lightImg, request);
			honer.setLight(light);
		}
		if(StringUtils.isNotBlank(grayImg.getOriginalFilename())){
			String gray = savePic(grayImg, request);
			honer.setGray(gray);
		}
		if (null != honer.getRange() && 0 <= honer.getRange()){
			honer.setRange(null);
		}
		Long id = honerManager.save(honer);
		request.getSession().setAttribute("showMessage", "修改成功！");
		return "redirect:/toUpdateHoner?id=" + id;
	}
	
	private String savePic(MultipartFile lightImg, HttpServletRequest request){
		String originalFilename = lightImg.getOriginalFilename();
		//上传图片
		String realPath = request.getSession().getServletContext().getRealPath("/");
		if(lightImg!=null && originalFilename!=null && originalFilename.length()>0){
			String url = ImageUtils.saveImage(request, lightImg, realPath+"/images/honer/");
			url = "/images/honer/"+url;
			return url;
		}
		return null;
	}
	
	/**
	 * 查看荣誉勋章
	 * @param request
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/showPersonHoner",method = RequestMethod.GET)
	public ApiResponse<Object> showPersonHoner(HttpServletRequest request,
			@RequestParam(value="userId", required=true)Long userId){
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
		List<Honer> list = honerManager.getHoner(userId);
		List<Object> object = sortListHoner(list);
		if (null == object) {
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		return ApiResponse.build(ResponseStatus.SUCCESS, object);
	}
	
	/**
	 * 发送荣誉勋章
	 * @param request
	 * @param userId
	 * @param honer
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sendHoner",method = RequestMethod.GET)
	public ApiResponse<Object> sendHoner(HttpServletRequest request,
			@RequestParam(value="userId", required=true)Long userId,
			@RequestParam(value="honerId", required=true)Long honerId){
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
		Honer honer = honerManager.getHonerById(honerId);
		if (!honer.getIsClickSend()){
			return ApiResponse.build(ResponseStatus.PERMISSION, null);
		}
		String honerString = String.valueOf(honerId);
		UserInfo userInfoById = userInfoManager.getUserInfoById(userId);
		String honerId2 = userInfoById.getHonerId();
		if(StringUtils.isBlank(honerId2)){
			userInfoById.setHonerId(honerString);
		} else {
			String[] split = userInfoById.getHonerId().split(",");
			for (String string : split) {
				if (string.equals(honerString)){
					return ApiResponse.build(ResponseStatus.FAIL, null);
				}
			}
			userInfoById.setHonerId(userInfoById.getHonerId()+","+honerString);
		}
		boolean result = userInfoManager.updateUserInfoById(userInfoById);
		if(result){
			return ApiResponse.build(ResponseStatus.SUCCESS, null);
		}
		return ApiResponse.build(ResponseStatus.FAIL, null);
	}
}
