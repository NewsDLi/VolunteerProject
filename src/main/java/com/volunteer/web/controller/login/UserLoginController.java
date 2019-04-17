package com.volunteer.web.controller.login;

import com.alibaba.fastjson.JSON;
import com.feilong.core.TimeInterval;
import com.feilong.core.Validator;
import com.feilong.servlet.http.RequestUtil;
import com.volunteer.cache.manager.CacheManager;
import com.volunteer.common.WechatMessage;
import com.volunteer.utils.HttpsUtils;
import com.volunteer.utils.PropBean;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yuan
 * @date 2019年4月15日15:08:52
 * @desc wx登录流程
 */
@RestController
public class UserLoginController {

    @Autowired
    private CacheManager cacheManager;

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
    public String mobileWechatLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "code",required = true) String code){
        Map<String, String> parameterMap = RequestUtil.getParameterSingleValueMap(request);
        //请求链接
        String returnUrl = parameterMap.get("returnUrl");
        try{
            if (Validator.isNullOrEmpty(returnUrl)){
                returnUrl = "";
            }
            //通过code换取用户信息--先从缓存中获取，没有就从第三方获取
            String httpResponse = cacheManager.getValue("WECHAT_MESSAGE" + code);
            if (httpResponse == null){
                httpResponse = getWechatMemberInfo(code);
            }
            if (Validator.isNullOrEmpty(httpResponse)){
                return "redirect:" + returnUrl;
            }
            WechatMessage wechatMessage = JSON.parseObject(httpResponse, WechatMessage.class);
            //查询信息失败
            if (wechatMessage.getErrcode() != 0){
                return "redirect:" + returnUrl;
            }
            //查询成功
            //如果是微信会员 ---将信息缓存起来
            if (cacheManager.getValue("WECHAT_MESSAGE" + code) == null){
                cacheManager.setValue("WECHAT_USERINFO" + code, httpResponse, TimeInterval.SECONDS_PER_HOUR * 2);
            }
            request.getSession().setAttribute("WECHAT_USERINFO", httpResponse);
//            return wechatOAuthSuccess(wechatMessage, request, response, returnUrl);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:" + returnUrl;

    }

    private String getWechatMemberInfo(String code){
        String httpResponse = null;
        try{
        String appid = prop.getAppid();
        String appsercet = prop.getAppid();
        String accessUrl = prop.getAccessToken()+"appid="+appid+"&secret="+appsercet+"&code="+code+"&grant_type=authorization_code";;
        String json = HttpsUtils.sendGet(accessUrl);
        JSONObject jsonObject = JSONObject.fromObject(json);
        String access_token = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        if(Validator.isNullOrEmpty(access_token)&&Validator.isNullOrEmpty(openid)){
            return httpResponse;
        }
        String get_userinfo = prop.getUserinfo()+"access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        get_userinfo = get_userinfo.replace("ACCESS_TOKEN", access_token);
        get_userinfo = get_userinfo.replace("OPENID", openid);
        httpResponse =  HttpsUtils.sendGet(get_userinfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return httpResponse;
    }

}
