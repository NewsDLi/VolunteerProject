package com.volunteer.intecepter;

import com.volunteer.constant.WxLoginConstant;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author NewsDLee
 * @date 2019年4月14日16:10:45
 * @desc 登录拦截
 *
 */
public class LoginIntecepter implements HandlerInterceptor {

	// 暂时注释 by NewsDLee
	// 进入 Handler方法之前执行
	// 用于身份认证、身份授权
	// 比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		HttpSession session = request.getSession();
//		String user = (String) session.getAttribute(WxLoginConstant.WECHAT_USERINFO_SESSION); //获取登录的session信息
//		if(user!=null){
//			return true;
//		}
//		else{
//			response.sendRedirect(request.getContextPath()+"/login/index");  //未登录自动跳转界面
//			return false;
//		}
//	}
}
