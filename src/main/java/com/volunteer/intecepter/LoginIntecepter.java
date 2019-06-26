package com.volunteer.intecepter;

import com.alibaba.fastjson.JSON;
import com.feilong.core.Validator;
import com.volunteer.constant.UserConstant;
import com.volunteer.constant.WxLoginConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoBind;
import com.volunteer.model.WechatInfo;
import com.volunteer.web.dao.UserInfoMapper;
import com.volunteer.web.manager.HonerManagerImpl;
import com.volunteer.web.manager.UserInfoBindManager;
import com.volunteer.web.manager.UserInfoManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 
 * @author NewsDLee
 * @date 2019年4月14日16:10:45
 * @desc 登录拦截
 *
 */
public class LoginIntecepter implements HandlerInterceptor {

	private final static Logger logger = LoggerFactory.getLogger(LoginIntecepter.class);
	
	@Autowired
	private UserInfoBindManager userInfoBindManager;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private UserInfoManager userInfoManager;

//	 暂时注释 by NewsDLee
//	 进入 Handler方法之前执行
//	 用于身份认证、身份授权
//	 比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute(UserConstant.LOGIN_PHONE); //获取登录的session信息
		if(user!=null){
			return true;
		}
		WechatInfo wechatInfo = (WechatInfo) request.getSession().getAttribute(WxLoginConstant.WECHAT_USERINFO_SESSION);
		logger.info("用户微信相关信息：{}", JSON.toJSONString(wechatInfo));
		if(Validator.isNullOrEmpty(wechatInfo)){
			response.sendRedirect("/index");  //未登录自动跳转界面
			return false;
		}
		if (null == wechatInfo.getId()){
			logger.error("微信信息id为空");
			return false;
		}
		List<UserInfoBind> userInfoBinds = userInfoBindManager.selectUserInfoBind(wechatInfo.getId());
		if(Validator.isNullOrEmpty(userInfoBinds)){
			response.sendRedirect("/index");  //未登录自动跳转界面
			return false;
		}
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userInfoBinds.get(0).getUserId());
		if(Validator.isNullOrEmpty(userInfo)){
			session.setAttribute(UserConstant.LOGIN_PHONE,userInfo);
			return true;
		}
		response.sendRedirect("/index");  //未登录自动跳转界面
			return false;

	}
}
