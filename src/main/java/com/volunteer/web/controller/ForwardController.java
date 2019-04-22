package com.volunteer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author NewsDLee
 * @date 2019年4月21日01:43:19
 * @desc 用来做页面跳转的controller
 *
 */
@Controller
public class ForwardController {
	
	/**
	 * 跳转至首页
	 * @return
	 */
	@RequestMapping("/index")
	public String toIndex(){
		return "index";
	}
	
	/**
	 * 跳转至微信登录页面
	 * @return
	 */
	@RequestMapping("/wechartLogin")
	public String toWechartLogin(){
		return "wechartLogin";
	}
	/**
	 * 跳转至微信登录页面
	 * @return
	 */
	@RequestMapping("/mypage")
	public String mypage(){
		return "mypage";
	}
	/**
	 * 跳转至个人信息
	 * @return
	 */
	@RequestMapping("/myinfo")
	public String myInfo(){
		return "myinfo";
	}
	/**
	 * 跳转至义工生涯
	 * @return
	 */
	@RequestMapping("/mycareer")
	public String mycareer(){
		return "mycareer";
	}
	/**
	 * 跳转至文件上传
	 * @return
	 */
	@RequestMapping("/import.htm")
	public String upload(){
		return "import";
	}
}
