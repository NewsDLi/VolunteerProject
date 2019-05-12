package com.volunteer.web.dao;

import com.volunteer.model.ArticleMessageBoard;
import com.volunteer.model.ArticleMessageBoardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleMessageBoardMapper {
    int countByExample(ArticleMessageBoardExample example);

    int deleteByExample(ArticleMessageBoardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ArticleMessageBoard record);

    int insertSelective(ArticleMessageBoard record);

    List<ArticleMessageBoard> selectByExample(ArticleMessageBoardExample example);

    ArticleMessageBoard selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ArticleMessageBoard record, @Param("example") ArticleMessageBoardExample example);

    int updateByExample(@Param("record") ArticleMessageBoard record, @Param("example") ArticleMessageBoardExample example);

    int updateByPrimaryKeySelective(ArticleMessageBoard record);

    int updateByPrimaryKey(ArticleMessageBoard record);
}