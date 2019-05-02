package com.volunteer.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.volunteer.constant.UserConstant;
import com.volunteer.model.MessageBoardCommand;
import com.volunteer.model.UserInfo;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
import com.volunteer.web.manager.MessageBoardManager;

/**
 * @author NewsDLee
 * @date 2019年5月2日20:49:38
 * @desc 留言板相关
 */
@Controller
public class MessageBoardController {

	@Autowired
	private MessageBoardManager messageBoardManager;
	
	@ResponseBody
	@RequestMapping(value = "/messageBoard/content", method = RequestMethod.POST)
	public ApiResponse<Object> SubmitMessage(HttpServletRequest request, String content){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		int insertContent = messageBoardManager.insertContent(userInfo.getId(), content);
		if(insertContent != 1){
			return ApiResponse.build(ResponseStatus.FAIL, null);
		}
		
		List<MessageBoardCommand> allMessageBoard = messageBoardManager.getAllMessageBoard(userInfo.getId());
		if(null == allMessageBoard || allMessageBoard.size() == 0){
			return ApiResponse.build(ResponseStatus.PERMISSION, null);
		}
		return ApiResponse.build(ResponseStatus.SUCCESS, allMessageBoard);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllMessageBoard", method = RequestMethod.GET)
	public ApiResponse<Object> getAllMessageBoard(HttpServletRequest request){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		List<MessageBoardCommand> list = messageBoardManager.getAllMessageBoard(userInfo.getId());
		if(null == list || list.size() == 0){
			return ApiResponse.build(ResponseStatus.PERMISSION, null);
		}
		return ApiResponse.build(ResponseStatus.SUCCESS, list);
	}
}
