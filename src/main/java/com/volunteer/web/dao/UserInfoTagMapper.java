package com.volunteer.web.dao;

import com.volunteer.model.UserInfoTag;
import com.volunteer.model.UserInfoTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoTagMapper {
    int countByExample(UserInfoTagExample example);

    int deleteByExample(UserInfoTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserInfoTag record);

    int insertSelective(UserInfoTag record);

    List<UserInfoTag> selectByExample(UserInfoTagExample example);

    UserInfoTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserInfoTag record, @Param("example") UserInfoTagExample example);

    int updateByExample(@Param("record") UserInfoTag record, @Param("example") UserInfoTagExample example);

    int updateByPrimaryKeySelective(UserInfoTag record);

    int updateByPrimaryKey(UserInfoTag record);
}