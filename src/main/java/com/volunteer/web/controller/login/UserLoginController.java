package com.volunteer.web.controller.login;

import com.alibaba.fastjson.JSON;
import com.feilong.core.TimeInterval;
import com.feilong.core.Validator;
import com.feilong.servlet.http.RequestUtil;
import com.volunteer.cache.manager.CacheManager;
import com.volunteer.common.UserInfoBindCommand;
import com.volunteer.common.WechatMessage;
import com.volunteer.constant.WxLoginConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoBind;
import com.volunteer.model.WechatInfo;
import com.volunteer.utils.HttpsUtils;
import com.volunteer.utils.PropBean;
import com.volunteer.web.controller.login.handler.WeChatLoginHandler;
import com.volunteer.web.manager.UserInfoBindManager;
import com.volunteer.web.manager.UserInfoManager;
import com.volunteer.web.manager.WechatInfoManager;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yuan
 * @date 2019年4月15日15:08:52
 * @desc wx登录流程
 */
@Controller
public class UserLoginController {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private WeChatLoginHandler weChatLoginHandler;

    @Autowired
    private UserInfoManager userInfoManager;

    @Autowired
    private WechatInfoManager wechatInfoManager;

    @Autowired
    private UserInfoBindManager userInfoBindManager;

    @Autowired
    private PropBean prop;

    /**
     * 微信公众号登录
     *
     * @param request
     * @param response
     * @param code
     * @return
     */
    @RequestMapping(value = "/weChatLogin")
    public String mobileWechatLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "code", required = true) String code) {
        Map<String, String> parameterMap = RequestUtil.getParameterSingleValueMap(request);
        //请求链接
        String returnUrl = parameterMap.get("returnUrl");
        try {
            if (StringUtils.isBlank(returnUrl)) {
                returnUrl = "";
            }
            //通过code换取用户信息--先从缓存中获取，没有就从第三方获取
            String httpResponse = cacheManager.getValue(WxLoginConstant.WECHAT_USERINFO + code);
            if (httpResponse == null) {
                httpResponse = getWechatMemberInfo(code);
            }
            if (StringUtils.isBlank(httpResponse)) {
                return "login";
            }
            WechatMessage wechatMessage = JSON.parseObject(httpResponse, WechatMessage.class);
            //查询信息失败
            if (Validator.isNullOrEmpty(wechatMessage)) {
                return "login";
            }
            //查询成功
            //如果是微信会员 ---将信息缓存起来
            if (cacheManager.getValue(WxLoginConstant.WECHAT_USERINFO + code) == null) {
                cacheManager.setValue(WxLoginConstant.WECHAT_USERINFO + code, httpResponse, TimeInterval.SECONDS_PER_HOUR * 2);
            }
            request.getSession().setAttribute(WxLoginConstant.WECHAT_USERINFO_SESSION, httpResponse);
            //登录用户的处理方法
            UserInfoBindCommand userInfo = weChatLoginHandler.wechatOAuthSuccess(wechatMessage);
            //通过openId查询是否有用户信息，，判断为第一次登陆
            if(Validator.isNullOrEmpty(userInfo)){
                request.setAttribute("wechatInfoId",saveWechatInfo(wechatMessage));
                return  "login";
            }
            return "redirect:" + returnUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "login";

    }


    /**
     * 跳转至登录页面
     * @return
     */
    @PostMapping(value = "/login.json")
    public String toLogin(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam(value = "wechatInfoId",required=false) Long wechatId,
                          @RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password){

        UserInfo userInfo = userInfoManager.getUserInfoByMobile(username);
        if(Validator.isNullOrEmpty(wechatId)){
            return "index";
        }
        UserInfoBind userInfoBind = new UserInfoBind();
        userInfoBind.setUserId(userInfo.getId());
        userInfoBind.setWechatId(wechatId);
        //绑定微信
        userInfoBindManager.saveUserInfoBind(userInfoBind);

        return "index";
    }



    private String getWechatMemberInfo(String code) {
        String httpResponse = null;
        try {
            //从配置文件中拿信息
            String appid = prop.getAppid();
            String appsercet = prop.getAppid();
            String accessUrl = prop.getAccessToken() + "appid=" + appid + "&secret=" + appsercet + "&code=" + code + "&grant_type=authorization_code";
            //获取accessToken，openId
            String json = HttpsUtils.sendGet(accessUrl);
            JSONObject jsonObject = JSONObject.fromObject(json);
            if (WxLoginConstant.WECHAT_ACCESS_SUCCESS.equals(jsonObject.get("errcode"))) {
                return httpResponse;
            }
            String access_token = jsonObject.getString("access_token");
            String openid = jsonObject.getString("openid");
            if (StringUtils.isBlank(access_token) && StringUtils.isBlank(openid)) {
                return httpResponse;
            }
            //获取用户信息
            String get_userinfo = prop.getUserinfo() + "access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
            get_userinfo = get_userinfo.replace("ACCESS_TOKEN", access_token);
            get_userinfo = get_userinfo.replace("OPENID", openid);
            httpResponse = HttpsUtils.sendGet(get_userinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpResponse;
    }

    private Long saveWechatInfo(WechatMessage wechatMessage){
        WechatInfo wechatInfo = new WechatInfo();
        try {
            wechatInfo.setOpenId(wechatMessage.getOpenId());
            wechatInfo.setNickName(wechatMessage.getNickname());
            wechatInfo.setCity(wechatMessage.getCity());
            wechatInfo.setCountry(wechatMessage.getCountry());
            wechatInfo.setProvince(wechatMessage.getProvince());
            wechatInfo.setSex(wechatMessage.getSex());
            wechatInfoManager.saveWechatInfo(wechatInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Validator.isNullOrEmpty(wechatInfo.getId())?0:wechatInfo.getId();
    }

}
