package com.volunteer.web.controller.login.handler;

import com.feilong.core.TimeInterval;
import com.feilong.core.Validator;
import com.volunteer.cache.manager.CacheManager;
import com.volunteer.common.UserInfoBindCommand;
import com.volunteer.common.WechatMessage;
import com.volunteer.constant.UserConstant;
import com.volunteer.constant.WxLoginConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.model.WechatInfo;
import com.volunteer.web.manager.UserInfoManager;
import com.volunteer.web.manager.WechatInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class WeChatLoginHandler {

    @Autowired
    private UserInfoManager userInfoManager;

    @Autowired
    private WechatInfoManager wechatInfoManager;

    @Autowired
    private CacheManager cacheManager;


    public UserInfo wechatOAuthSuccess(HttpServletRequest request, WechatInfo wechatInfo){
        UserInfo userInfo = userInfoManager.getUserInfoByOpenId(wechatInfo,request);
        if(Validator.isNullOrEmpty(userInfo)){
            return  null;
        }
        request.getSession().setAttribute(UserConstant.LOGIN_PHONE,userInfo);
        return  userInfo;

   }

}
