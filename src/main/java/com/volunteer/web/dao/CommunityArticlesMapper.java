package com.volunteer.web.dao;

import com.volunteer.model.CommunityArticles;
import com.volunteer.model.CommunityArticlesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommunityArticlesMapper {
    int countByExample(CommunityArticlesExample example);

    int deleteByExample(CommunityArticlesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommunityArticles record);

    int insertSelective(CommunityArticles record);

    List<CommunityArticles> selectByExampleWithBLOBs(CommunityArticlesExample example);

    List<CommunityArticles> selectByExample(CommunityArticlesExample example);

    CommunityArticles selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommunityArticles record, @Param("example") CommunityArticlesExample example);

    int updateByExampleWithBLOBs(@Param("record") CommunityArticles record, @Param("example") CommunityArticlesExample example);

    int updateByExample(@Param("record") CommunityArticles record, @Param("example") CommunityArticlesExample example);

    int updateByPrimaryKeySelective(CommunityArticles record);

    int updateByPrimaryKeyWithBLOBs(CommunityArticles record);

    int updateByPrimaryKey(CommunityArticles record);
}