package com.volunteer.web.dao;

import com.volunteer.model.UserInfoBind;
import com.volunteer.model.UserInfoBindExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoBindMapper {
    int countByExample(UserInfoBindExample example);

    int deleteByExample(UserInfoBindExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserInfoBind record);

    int insertSelective(UserInfoBind record);

    List<UserInfoBind> selectByExample(UserInfoBindExample example);

    UserInfoBind selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserInfoBind record, @Param("example") UserInfoBindExample example);

    int updateByExample(@Param("record") UserInfoBind record, @Param("example") UserInfoBindExample example);

    int updateByPrimaryKeySelective(UserInfoBind record);

    int updateByPrimaryKey(UserInfoBind record);
}