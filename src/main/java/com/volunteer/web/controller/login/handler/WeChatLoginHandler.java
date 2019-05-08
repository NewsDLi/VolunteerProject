package com.volunteer.web.controller.login.handler;

import com.feilong.core.TimeInterval;
import com.feilong.core.Validator;
import com.volunteer.cache.manager.CacheManager;
import com.volunteer.common.UserInfoBindCommand;
import com.volunteer.common.WechatMessage;
import com.volunteer.constant.UserConstant;
import com.volunteer.constant.WxLoginConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.web.manager.UserInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WeChatLoginHandler {

    @Autowired
    private UserInfoManager userInfoManager;


    @Autowired
    private CacheManager cacheManager;


    public UserInfo wechatOAuthSuccess(HttpServletRequest request, WechatMessage wechatMessage){
        UserInfo userInfo = cacheManager.getObject(WxLoginConstant.USERINFO+wechatMessage.getOpenId());
        if(Validator.isNotNullOrEmpty(userInfo)){
            return userInfo;
        }
        userInfo = userInfoManager.getUserInfoByOpenId(wechatMessage);
        if(Validator.isNullOrEmpty(userInfo)){
            return  userInfo;
        }
        request.getSession().setAttribute(UserConstant.LOGIN_PHONE,userInfo);
        cacheManager.setObject(WxLoginConstant.USERINFO+wechatMessage.getOpenId(),userInfo, TimeInterval.SECONDS_PER_HOUR*2);
        return  userInfo;

   }

}
