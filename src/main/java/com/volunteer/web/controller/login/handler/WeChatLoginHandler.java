package com.volunteer.web.controller.login.handler;

import com.alibaba.fastjson.JSON;
import com.feilong.core.Validator;
import com.volunteer.cache.manager.CacheManager;
import com.volunteer.constant.UserConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.model.WechatInfo;
import com.volunteer.web.controller.UserInfoController;
import com.volunteer.web.manager.UserInfoManager;
import com.volunteer.web.manager.WechatInfoManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WeChatLoginHandler {

	private Logger LOGGER = LoggerFactory.getLogger(WeChatLoginHandler.class);
	
    @Autowired
    private UserInfoManager userInfoManager;

    @Autowired
    private WechatInfoManager wechatInfoManager;

    @Autowired
    private CacheManager cacheManager;


    public UserInfo wechatOAuthSuccess(HttpServletRequest request, WechatInfo wechatInfo){
        UserInfo userInfo = userInfoManager.getUserInfoByOpenId(wechatInfo,request);
        LOGGER.info("通过微信id获取用户信息,{}", JSON.toJSONString(userInfo));
        if(Validator.isNullOrEmpty(userInfo)){
        	LOGGER.warn("用户信息为空！");
            return  null;
        }
        request.getSession().setAttribute(UserConstant.LOGIN_PHONE,userInfo);
        return  userInfo;

   }

}
