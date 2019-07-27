package com.volunteer.web.controller;

import com.feilong.core.Validator;
import com.volunteer.constant.CommonConstant;
import com.volunteer.constant.UserConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.model.WheelPlanting;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
import com.volunteer.web.manager.WheelPlantingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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


	@PostMapping("/updateWhellPlanting")
	@ResponseBody
	public ApiResponse<Object> updateWhellPlanting(HttpServletRequest request, Model model,@RequestBody WheelPlanting wheelPlanting){
		if(Validator.isNullOrEmpty(wheelPlanting)){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		String picName = UUID.randomUUID().toString()+".png";
		String imagePath= CommonConstant.UPLOAD_PIC_URL+"/images/lunbo/" + picName;
		wheelPlanting.setVersion(new Date());
		if(Validator.isNullOrEmpty(wheelPlanting.getPic())){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		boolean generateImage = GenerateImage(wheelPlanting.getPic(), imagePath);
		wheelPlanting.setPic(picName);
		Integer updateWheelPlanting = wheelPlantingManager.updateWheelPlanting(wheelPlanting);
		model.addAttribute("whellPlanting", updateWheelPlanting);
		if(updateWheelPlanting.equals(0) || generateImage){
			return ApiResponse.build(ResponseStatus.FAIL, updateWheelPlanting);
		}
		return ApiResponse.build(ResponseStatus.SUCCESS, updateWheelPlanting);
	}

	/**
	 * @Description： base64字符串转化成图片
	 * @param:     imgStr
	 * @Return:
	 */
	public boolean GenerateImage(String imgStr,String imagePath)
	{
		//对字节数组字符串进行Base64解码并生成图片
		//图像数据为空
		if (imgStr == null){
			return false;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try
		{
			//Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for(int i=0;i<b.length;++i)
			{
				if(b[i]<0)
				{
					//调整异常数据
					b[i]+=256;
				}
			}
			//新生成的图片
			OutputStream out = new FileOutputStream(imagePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		}
		catch (Exception e)
		{
			LOGGER.error("message", e);
			return false;
		}
	}

}
