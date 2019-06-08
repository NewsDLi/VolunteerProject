package com.volunteer.web.controller.login;

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
import com.volunteer.utils.HttpsUtils;
import com.volunteer.utils.PropBean;
import com.volunteer.web.controller.login.handler.WeChatLoginHandler;
import com.volunteer.web.dao.HonerMapper;
import com.volunteer.web.dao.WechatInfoMapper;
import com.volunteer.web.manager.HonerManager;
import com.volunteer.web.manager.UserInfoBindManager;
import com.volunteer.web.manager.UserInfoManager;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private WechatInfoMapper wechatInfoMapper;


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
    @RequestMapping(value = "/weChatLogin")
    public String mobileWechatLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "code", required = true) String code) {
        try {
            //判断是否登录
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
            if(Validator.isNotNullOrEmpty(userInfo)){
                return "mypage";
            }
            //通过code换取用户信息--先从缓存中获取，没有就从第三方获取
            WechatInfo wechatInfo = (WechatInfo)request.getSession().getAttribute(WxLoginConstant.WECHAT_USERINFO_SESSION);
            if(Validator.isNotNullOrEmpty(wechatInfo)){
            List<UserInfoBind> userInfoBinds = userInfoBindManager.selectUserInfoBind(wechatInfo.getId());
            if(Validator.isNotNullOrEmpty(userInfoBinds)){
                UserInfo userInfo1 = userInfoManager.selectByPrimaryKey(userInfoBinds.get(0).getUserId());
                return "mypage";
            }
            }
            String httpResponse = null;
            if (Validator.isNullOrEmpty(wechatInfo)) {
                httpResponse = getWechatMemberInfo(code);
            }

            if (StringUtils.isBlank(httpResponse)) {
                return "index";
            }
            WechatMessage wechatMessage = JSON.parseObject(httpResponse, WechatMessage.class);
            //查询信息失败
            if (Validator.isNullOrEmpty(wechatMessage.getOpenId())) {
                return "index";
            }
            WechatInfo wechatInfo2 = bulidWechatInfo(wechatMessage);
            if (Validator.isNullOrEmpty(wechatInfo2)){
                return "index";
            }

            //登录用户的处理方法
            UserInfo userInfos = weChatLoginHandler.wechatOAuthSuccess(request, wechatInfo2);
            //通过openId查询是否有用户信息，，判断为第一次登陆
            if (Validator.isNullOrEmpty(userInfos)) {
                return "index";
            }
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
                          @RequestParam(value = "password") String password) {

        List<UserInfo> userInfoByMobile = userInfoManager.getUserInfoByMobile(username);
        if (null == userInfoByMobile || userInfoByMobile.size() == 0 || Validator.isNullOrEmpty(password)) {
            return "index";
        }
        //通过code换取用户信息--先从缓存中获取，没有就从第三方获取
        //微信绑定
        UserInfo userInfo = userInfoByMobile.get(0);
        if (!validPassword(userInfo,password)){
            return "index";
        }
        HttpSession session = request.getSession();
        WechatInfo wechatInfo = (WechatInfo) session.getAttribute(WxLoginConstant.WECHAT_USERINFO_SESSION);
        if (Validator.isNullOrEmpty(wechatInfo)){
            return returnLogin(session,userInfo);
        }
        List<UserInfoBind> userInfoBinds = userInfoBindManager.selectUserInfoBind(wechatInfo.getId());
        if(Validator.isNullOrEmpty(userInfoBinds)){
            UserInfoBind userInfoBind = new UserInfoBind();
            userInfoBind.setUserId(userInfo.getId());
            userInfoBind.setWechatId(wechatInfo.getId());
            userInfoBindManager.saveUserInfoBind(userInfoBind);
        }
        return returnLogin(session,userInfo);
    }

    private String returnLogin(HttpSession session,UserInfo userInfo){
        session.setAttribute(UserConstant.LOGIN_PHONE, userInfo);
        honerManager.updateUserHonerInfo(userInfo.getId());
        return "mypage";
    }

    private Boolean validPassword(UserInfo userInfo,String password){
        String idCard = AESUtil.AESDncode(AESUtil.KEY,userInfo.getIdCard());
        Pattern p = Pattern.compile("[0-9]+[X|x]{1}");
        Matcher m = p.matcher(idCard);
        boolean b = m.matches();
        if(b){
            idCard = idCard.substring(idCard.length()-7,idCard.length()-1);
        }else{
            idCard = idCard.substring(idCard.length()-6);
        }
        if(idCard.equals(password)){
            return true;
        }
        return false;
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
