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
       //通过openId查询是否有用户信息，，判断为第一次登陆
       if(Validator.isNullOrEmpty(userInfo)){
           return  "login";
       }

       return returnUrl;
   }

}
