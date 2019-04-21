package com.volunteer.constant;

/**
 * 
 * @author yuan
 * @date 2019年4月14日16:23:34
 * @desc 登录常亮
 */
public class WxLoginConstant {
    /**
     * 存放在session中的用户信息
     */
    public static final String WECHAT_USERINFO_SESSION = "WechatUserInfoSession";
    /**
     * 存放在redis中的微信用户信息
     */
    public static final String WECHAT_USERINFO = "WechatUserInfo";
    /**
     * 微信接口成功code
     */
    public static final Integer WECHAT_ACCESS_SUCCESS = 27000;

    /**
     * 存放在redis中的用户信息
     */
    public static final String USERINFO = "userinfo";
}
