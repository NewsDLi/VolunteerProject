package com.volunteer.web.controller.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.feilong.core.Validator;
import com.volunteer.cache.manager.CacheManager;
import com.volunteer.common.WechatMessage;
import com.volunteer.constant.UserConstant;
import com.volunteer.constant.WxLoginConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoBind;
import com.volunteer.model.WechatInfo;
import com.volunteer.utils.AESUtil;
import com.volunteer.utils.CookieUtil;
import com.volunteer.utils.HttpsUtils;
import com.volunteer.utils.PropBean;
import com.volunteer.web.controller.login.handler.WeChatLoginHandler;
import com.volunteer.web.manager.HonerManager;
import com.volunteer.web.manager.UserInfoBindManager;
import com.volunteer.web.manager.UserInfoManager;
import com.volunteer.web.manager.WechatInfoManager;

import net.sf.json.JSONObject;

/**
 * @author yuan
 * @date 2019年4月15日15:08:52
 * @desc wx登录流程
 */
@Controller
public class UserLoginController {

	private Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);
	
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
	private HonerManager honerManager;

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
//    @RequestMapping(value = "/weChatLogin")
    public String mobileWechatLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "code", required = true) String code) {
    	LOGGER.info("开始微信登录...");
        try {
            //判断是否登录
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
            if(Validator.isNotNullOrEmpty(userInfo)){
            	LOGGER.warn("用户已经登录");
                return "mypage";
            }
            //通过code换取用户信息--先从缓存中获取，没有就从第三方获取
            WechatInfo wechatInfo = (WechatInfo)request.getSession().getAttribute(WxLoginConstant.WECHAT_USERINFO_SESSION);
            if(Validator.isNotNullOrEmpty(wechatInfo)){
            	LOGGER.info("缓存中的微信信息为：{}", JSON.toJSONString(wechatInfo));
                if(Validator.isNullOrEmpty(wechatInfo.getId())){
                    UserInfo userInfos = weChatLoginHandler.wechatOAuthSuccess(request, wechatInfo);
                    //通过openId查询是否有用户信息，，判断为第一次登陆
                    if (Validator.isNullOrEmpty(userInfos)) {
                        return "index";
                    }
                }
	            List<UserInfoBind> userInfoBinds = userInfoBindManager.selectUserInfoBind(wechatInfo.getId());
	            LOGGER.info("查询用户绑定信息：{}", JSON.toJSONString(userInfoBinds));
	            if(Validator.isNotNullOrEmpty(userInfoBinds)){
	                UserInfo userInfo1 = userInfoManager.selectByPrimaryKey(userInfoBinds.get(0).getUserId());
	                LOGGER.info("查询的相关用户信息：{}", JSON.toJSONString(userInfo1));
	                return "mypage";
	            }
            }
            String httpResponse = null;
            if (Validator.isNullOrEmpty(wechatInfo)) {
            	LOGGER.info("微信信息为空重新获取微信信息...");
                httpResponse = getWechatMemberInfo(code);
                LOGGER.info("微信用户信息:{}", httpResponse);
            }

            if (StringUtils.isBlank(httpResponse)) {
            	LOGGER.info("httpResponse为空，返回首页");
                return "index";
            }
            WechatMessage wechatMessage = JSON.parseObject(httpResponse, WechatMessage.class);
            //查询信息失败
            if (Validator.isNullOrEmpty(wechatMessage.getOpenId())) {
            	LOGGER.info("wechatMessage中openid为空，返回首页！，wechatMessage：{}", JSON.toJSONString(wechatMessage));
                return "index";
            }
            WechatInfo wechatInfo2 = bulidWechatInfo(wechatMessage);
            if (Validator.isNullOrEmpty(wechatInfo2)){
            	LOGGER.info("wechatInfo2为空，返回首页！，wechatInfo2：{}", JSON.toJSONString(wechatInfo2));
                return "index";
            }

            LOGGER.info("开始根据微信信息获取相关用户信息...");
            //登录用户的处理方法
            UserInfo userInfos = weChatLoginHandler.wechatOAuthSuccess(request, wechatInfo2);
            //通过openId查询是否有用户信息，，判断为第一次登陆
            if (Validator.isNullOrEmpty(userInfos)) {
            	LOGGER.info("用户信息为空，返回首页！");
                return "index";
            }
            LOGGER.info("根据微信信息获取会员信息成功，微信信息:{},会员信息:{}",JSON.toJSONString(wechatInfo2), JSON.toJSONString(userInfos));
            return "mypage";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";

    }
    private WechatInfo bulidWechatInfo(WechatMessage wechatMessage){
        WechatInfo wechatInfo = new WechatInfo();
        wechatInfo.setOpenId(wechatMessage.getOpenId());
        wechatInfo.setNickName(wechatMessage.getNickname());
        wechatInfo.setSex(wechatMessage.getSex());
        wechatInfo.setProvince(wechatMessage.getProvince());
        wechatInfo.setCountry(wechatMessage.getCountry());
        wechatInfo.setCity(wechatMessage.getCity());
        wechatInfo.setImage(wechatMessage.getHeadImgUrl());
        return wechatInfo;
    }

    /**
     * 跳转至登录页面
     *
     * @return
     */
    @PostMapping(value = "/login.json")
    public String toLogin(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam(value = "wechatInfoId", required = false) Long wechatId,
                          @RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password,
                          @RequestParam(value = "isRemember") boolean isRemember) {

    	LOGGER.info("开始用户登录...");
        List<UserInfo> userInfoByMobile = userInfoManager.getUserInfoByMobile(username);
        LOGGER.info("查询用户信息为：{}", JSON.toJSONString(userInfoByMobile));
        if (null == userInfoByMobile || userInfoByMobile.size() == 0 || Validator.isNullOrEmpty(password)) {
        	LOGGER.info("用户信息为空，返回首页");
            return "index";
        }
        //通过code换取用户信息--先从缓存中获取，没有就从第三方获取
        //微信绑定
        UserInfo userInfo = userInfoByMobile.get(0);
        if (!validPassword(userInfo,password)){
        	LOGGER.info("校验密码出错返回首页");
            return "index";
        }
        // 保存用户信息到cookie中
        saveUserToCookie(username, password, isRemember, request, response);
        HttpSession session = request.getSession();
        WechatInfo wechatInfo = (WechatInfo) session.getAttribute(WxLoginConstant.WECHAT_USERINFO_SESSION);
        LOGGER.info("获取用户微信信息：{}", JSON.toJSONString(wechatInfo));
        if (Validator.isNullOrEmpty(wechatInfo)){
        	// 这里添加“微信”信息到session中
        	wechatInfo = wechatInfoManager.getWechartInfoById(userInfo);
        	session.setAttribute(WxLoginConstant.WECHAT_USERINFO_SESSION, wechatInfo);;
            return returnLogin(session,userInfo);
        }
        List<UserInfoBind> userInfoBinds = userInfoBindManager.selectUserInfoBind(wechatInfo.getId());
        LOGGER.info("查询用户微信绑定信息：{}", JSON.toJSONString(userInfoBinds));
        if(Validator.isNullOrEmpty(userInfoBinds)){
        	LOGGER.info("用户绑定信息为空，进行信息绑定");
            UserInfoBind userInfoBind = new UserInfoBind();
            userInfoBind.setUserId(userInfo.getId());
            userInfoBind.setWechatId(wechatInfo.getId());
            userInfoBindManager.saveUserInfoBind(userInfoBind);
        }
        LOGGER.info("进行用户信息登录");
        return returnLogin(session,userInfo);
    }

    private void saveUserToCookie(String username, 
    		String password, 
    		boolean isRemember, 
    		HttpServletRequest request,
            HttpServletResponse response) {
		try{
			if (isRemember){
				// cookie过期时间为1年
				LOGGER.info("保存用户信息到cookie中");
				CookieUtil.setCookieValue("p_w", password, request, response, 365*24*60*60, false, false);
				CookieUtil.setCookieValue("u_n", username, request, response, 365*24*60*60, false, false);
			} else {
				LOGGER.info("删除用户cookie中的信息");
				CookieUtil.deleteCookie(request, response, "p_w");
				CookieUtil.deleteCookie(request, response, "u_n");
			}
		}catch(Exception e) {
			LOGGER.error("保存用户信息到cookie报错：", e);
		}
	}
	private String returnLogin(HttpSession session,UserInfo userInfo){
    	LOGGER.info("设置用户登录信息：{}",JSON.toJSONString(userInfo));
        session.setAttribute(UserConstant.LOGIN_PHONE, userInfo);
        honerManager.updateUserHonerInfo(userInfo.getId());
        return "mypage";
    }

    private Boolean validPassword(UserInfo userInfo,String password){
        try{
        	LOGGER.info("开始校验密码是否正确...");
        	LOGGER.info("解密之前的身份证号为：{}", userInfo.getIdCard());
            String idCard = AESUtil.AESDncode(AESUtil.KEY,userInfo.getIdCard());
            LOGGER.info("解密后的身份证号为：{}", idCard);
            if (null == idCard){
            	 LOGGER.info("解密失败");
            	return false;
            }
            idCard = idCard.substring(idCard.length()-6);
            LOGGER.info("开始判断密码是否正确！");
            if(idCard.equals(password)){
            	LOGGER.info("密码正确！");
                return true;
            }
            LOGGER.info("密码错误！");
            return false;
        } catch	(Exception e){
        	LOGGER.error("密码解析出错：", e);
        	return false;
        }
    }


    /**
     * 跳转至登录页面
     *
     * @return
     */
    public String wxlogin(HttpServletRequest request,
                          HttpServletResponse response) {

        return "wechartLogin";
    }

    /**
     * 跳转至微信登录页面
     *
     * @return
     */
    @PostMapping(value = "/index")
    public String login(HttpServletRequest request,
                        HttpServletResponse response) {

        return "index";
    }


    private String getWechatMemberInfo(String code) {
        String httpResponse = null;
        try {
            //从配置文件中拿信息
            String appid = prop.getAppid();
            String appsercet = prop.getAppsecret();
            String accessUrl = prop.getAccessToken() + "code=" + code + "&appid=" + appid + "&secret=" + appsercet  + "&grant_type=authorization_code";
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

}
