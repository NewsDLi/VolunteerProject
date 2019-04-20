package com.volunteer.web.controller.login.handler;

import com.feilong.core.Validator;
import com.volunteer.common.WechatMessage;
import com.volunteer.model.UserInfo;
import com.volunteer.web.manager.UserInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class WeChatLoginHandler {

    @Autowired
    private UserInfoManager userInfoManager;


   public String wechatOAuthSuccess(WechatMessage wechatMessage, HttpServletRequest request, HttpServletResponse response,String returnUrl){

       UserInfo userInfo = userInfoManager.getUserInfoByOpenId(wechatMessage.getOpenId());
       if(Validator.isNullOrEmpty(userInfo)){
           UserInfo userInfoByMobile = userInfoManager.getUserInfoByMobile(wechatMessage.getMobile());
           return  Validator.isNullOrEmpty(userInfoByMobile) ? "/index": returnUrl;
       }

       if(userInfo.getLoginPhone().equals(wechatMessage.getMobile())){
           return  "/index";
       }
       return returnUrl;
   }
}
