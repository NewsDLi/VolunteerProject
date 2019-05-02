package com.volunteer.web.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volunteer.model.MessageBoard;
import com.volunteer.model.MessageBoardCommand;
import com.volunteer.model.MessageBoardExample;
import com.volunteer.web.dao.MessageBoardMapper;

@Service
public class MessageBoardManagerImpl implements MessageBoardManager{

	@Autowired
	private MessageBoardMapper messageBoardMapper;

	@Override
	public int insertContent(Long userId, String content) {
		MessageBoard messageBoard = new MessageBoard();
		messageBoard.setUserId(userId);
		messageBoard.setContent(content);
		messageBoard.setLifecycle(1);
		messageBoard.setVersion(new Date());
		return messageBoardMapper.insert(messageBoard);
	}

	@Override
	public List<MessageBoardCommand> getAllMessageBoard(Long userId) {
		// TODO userId 判断此用户是否有权限查看留言板信息
		
		return messageBoardMapper.getAllMessageBoard();
	}
}
