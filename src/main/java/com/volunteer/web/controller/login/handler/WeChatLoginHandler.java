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
           //通过Mobile查询是否有用户信息，，判断微信与数据库手机号是否绑定
           UserInfo userInfoByMobile = userInfoManager.getUserInfoByMobile(wechatMessage.getMobile());
           return  Validator.isNullOrEmpty(userInfoByMobile) ? "login": returnUrl;
       }

       if(userInfo.getLoginPhone().equals(wechatMessage.getMobile())){
           return  "index";
       }
       return returnUrl;
   }

   public Boolean saveWechatInfo()

   {

       return true;
    }
}
