package com.volunteer.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteer.cache.manager.CacheManager;
import com.volunteer.model.WechatInfo;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
import com.volunteer.web.manager.UserInfoManager;
import com.volunteer.web.manager.WechatInfoManager;

/**
 * @author NewsDLee
 * @date 2019年4月14日15:08:52
 * @desc userController测试框架是否运行正常
 */
@RestController
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserInfoManager userInfoManager;

	@Autowired
	private WechatInfoManager wechatInfoManager;

	@Autowired
	private CacheManager cacheManager;

	@RequestMapping("/setCache")
	public ApiResponse<String> setCache(HttpServletRequest request, HttpServletResponse response){
		cacheManager.setObject("asdasd", "asdasd");
		return ApiResponse.build(ResponseStatus.SUCCESS, null);
	}
	
	@RequestMapping("/getCache")
	public ApiResponse<String> getCache(HttpServletRequest request, HttpServletResponse response){
		String object = cacheManager.getObject("asdasd");
		return ApiResponse.build(ResponseStatus.SUCCESS, object);
	}

    @RequestMapping("/getUserInfo")
    public ApiResponse<Boolean> getUserInfo(HttpServletRequest request, HttpServletResponse response){
//        UserInfoBindCommand object = userInfoManager.getUserInfoByOpenId("111");
        WechatInfo wechatInfo= new WechatInfo();
        wechatInfo.setSex(1);
        wechatInfo.setId(1L);
        wechatInfo.setNickName("shiyuan");
        wechatInfo.setOpenId("shiyuan");
		wechatInfoManager.saveWechatInfo(wechatInfo);
        return ApiResponse.build(ResponseStatus.SUCCESS, null);
    }
	
}
