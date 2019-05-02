package com.volunteer.web.dao;

import com.volunteer.model.MessageBoard;
import com.volunteer.model.MessageBoardCommand;
import com.volunteer.model.MessageBoardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageBoardMapper {
    int countByExample(MessageBoardExample example);

    int deleteByExample(MessageBoardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MessageBoard record);

    int insertSelective(MessageBoard record);

    List<MessageBoard> selectByExample(MessageBoardExample example);

    MessageBoard selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MessageBoard record, @Param("example") MessageBoardExample example);

    int updateByExample(@Param("record") MessageBoard record, @Param("example") MessageBoardExample example);

    int updateByPrimaryKeySelective(MessageBoard record);

    int updateByPrimaryKey(MessageBoard record);

	List<MessageBoardCommand> getAllMessageBoard();
}