package com.volunteer.web.manager;

import java.util.List;

import com.volunteer.model.MessageBoardCommand;

public interface MessageBoardManager {

	int insertContent(Long id, String content);

	List<MessageBoardCommand> getAllMessageBoard(Long userId);

}
