package com.volunteer.web.controller;

import com.feilong.core.Validator;
import com.volunteer.constant.CommonConstant;
import com.volunteer.constant.UserConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.model.WheelPlanting;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
import com.volunteer.utils.ImageUtils;
import com.volunteer.web.manager.WheelPlantingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author NewsDLee
 * @desc 首页轮播管理
 */
@Controller
public class WheelPlantingController {

	private Logger LOGGER = LoggerFactory.getLogger(WheelPlantingController.class);

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
		List<WheelPlanting> list = wheelPlantingManager.queryAll();
		return ApiResponse.build(ResponseStatus.SUCCESS, list);
	}
	
	@RequestMapping("/getWhellPlantingById")
	public String getWhellPlantingById(HttpServletRequest request, Model model,
			@RequestParam(value="id", required=true)Long id){
		WheelPlanting whellPlanting = wheelPlantingManager.queryById(id);
		model.addAttribute("whellPlanting", whellPlanting);
		return "UpdateWhellPlanting";
	}


	@RequestMapping(value = "/updateWhellPlanting",method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ApiResponse<Object> updateWhellPlanting(HttpServletRequest request, Model model
			,@RequestParam(required = false) MultipartFile pics
			,@RequestParam Long id
			,@RequestParam String description
			,@RequestParam String linkAddress){

		if(
				Validator.isNullOrEmpty(id)||
				Validator.isNullOrEmpty(description)||
				Validator.isNullOrEmpty(linkAddress)){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		WheelPlanting wheelPlanting = new WheelPlanting();
		if(Validator.isNotNullOrEmpty(pics)){
			String savePic = savePic(pics, request);
			wheelPlanting.setPic(savePic);
		}
		wheelPlanting.setDescription(description);
		wheelPlanting.setId(id);
		wheelPlanting.setLinkAddress(linkAddress);
		wheelPlanting.setVersion(new Date());
		Integer updateWheelPlanting = wheelPlantingManager.updateWheelPlanting(wheelPlanting);
		model.addAttribute("whellPlanting", updateWheelPlanting);
		if(updateWheelPlanting.equals(0)){
			return ApiResponse.build(ResponseStatus.FAIL, updateWheelPlanting);
		}
		return ApiResponse.build(ResponseStatus.SUCCESS, updateWheelPlanting);


	}

	private String savePic(MultipartFile lightImg, HttpServletRequest request){
		String originalFilename = lightImg.getOriginalFilename();
		//上传图片
		String realPath = CommonConstant.UPLOAD_PIC_URL+"/images/lunbo/";
		LOGGER.info("文件上传的绝对路径：{}", realPath);
		if(lightImg!=null && originalFilename!=null && originalFilename.length()>0){
			String url = ImageUtils.saveImage(request, lightImg, realPath);
			LOGGER.info("文件保存的真是路径：{}", url);
			return url;
		}
		return null;
	}

}
