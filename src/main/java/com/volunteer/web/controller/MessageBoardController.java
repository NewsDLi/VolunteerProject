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
		// 是否有权限查看留言板
		List<MessageBoardCommand> allMessageBoard = null;
		if(userInfo.getIsMessageBoard()){
			allMessageBoard = getAllMessageBoard();
		}
		if(null == allMessageBoard || allMessageBoard.size() == 0){
			return ApiResponse.build(ResponseStatus.SUCCESS, null);
		}
		return ApiResponse.build(ResponseStatus.SUCCESS, allMessageBoard);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllMessageBoard", method = RequestMethod.GET)
	public ApiResponse<Object> getAllMessageBoard(HttpServletRequest request){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		// 是否有权限查看留言板
		List<MessageBoardCommand> allMessageBoard = null;
		if (null == userInfo.getIsMessageBoard()){
			return ApiResponse.build(ResponseStatus.PERMISSION, null);
		}
		if(userInfo.getIsMessageBoard()){
			allMessageBoard = getAllMessageBoard();
		}
		if(null == allMessageBoard || allMessageBoard.size() == 0){
			return ApiResponse.build(ResponseStatus.PERMISSION, null);
		}
		return ApiResponse.build(ResponseStatus.SUCCESS, allMessageBoard);
	}
	
	private List<MessageBoardCommand> getAllMessageBoard(){
		return messageBoardManager.getAllMessageBoard();
	}
}
