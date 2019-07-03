package com.volunteer.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.volunteer.constant.UserConstant;
import com.volunteer.model.MessageBoardCommand;
import com.volunteer.model.UserInfo;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
import com.volunteer.web.manager.MessageBoardManager;
import com.volunteer.web.manager.UserInfoManager;

/**
 * @author NewsDLee
 * @date 2019年5月2日20:49:38
 * @desc 留言板相关
 */
@Controller
public class MessageBoardController {
	
	private Logger LOGGER = LoggerFactory.getLogger(MessageBoardController.class);

	@Autowired
	private MessageBoardManager messageBoardManager;
	
	@Autowired
	private UserInfoManager userInfoManager;
	
	@ResponseBody
	@RequestMapping(value = "/messageBoard/content", method = RequestMethod.POST)
	public ApiResponse<Object> SubmitMessage(HttpServletRequest request, String content){
		LOGGER.info("开始进入提交留言信息功能...");
		UserInfo sessionUserInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		UserInfo userInfo = userInfoManager.selectByPrimaryKey(sessionUserInfo.getId());
		int insertContent = messageBoardManager.insertContent(userInfo.getId(), content);
		if(insertContent != 1){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		LOGGER.info("插入留言板信息成功...");
		// 是否有权限查看留言板
		List<MessageBoardCommand> allMessageBoard = null;
		if(userInfo.getIsMessageBoard()){
			LOGGER.info("具有留言板查看权限，获取所有留言板信息...");
			allMessageBoard = getAllMessageBoard();
		}
		if(null == allMessageBoard || allMessageBoard.size() == 0){
			LOGGER.info("不具有留言板查看权限。");
			return ApiResponse.build(ResponseStatus.SUCCESS, null);
		}
		LOGGER.info("返回留言板信息。");
		return ApiResponse.build(ResponseStatus.SUCCESS, allMessageBoard);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllMessageBoard", method = RequestMethod.GET)
	public ApiResponse<Object> getAllMessageBoard(HttpServletRequest request){
		LOGGER.info("开始进入查询留言板所有功能...");
		UserInfo sessionUserInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		UserInfo userInfo = userInfoManager.selectByPrimaryKey(sessionUserInfo.getId());
		LOGGER.info("当前登录用户信息：{}", JSON.toJSONString(userInfo));
		// 是否有权限查看留言板
		List<MessageBoardCommand> allMessageBoard = null;
		if (null == userInfo.getIsMessageBoard() || !userInfo.getIsMessageBoard()){
			LOGGER.info("不具备留言板查询权限.");
			return ApiResponse.build(ResponseStatus.PERMISSION, null);
		}
		if(userInfo.getIsMessageBoard()){
			LOGGER.info("获取所有留言板信息.");
			allMessageBoard = getAllMessageBoard();
		}
		if(null == allMessageBoard || allMessageBoard.size() == 0){
			LOGGER.info("留言板信息为空，或者不剧本权限.");
			return ApiResponse.build(ResponseStatus.PERMISSION, null);
		}
		LOGGER.info("返回所有留言板内容。");
		return ApiResponse.build(ResponseStatus.SUCCESS, allMessageBoard);
	}
	
	private List<MessageBoardCommand> getAllMessageBoard(){
		return messageBoardManager.getAllMessageBoard();
	}
}
