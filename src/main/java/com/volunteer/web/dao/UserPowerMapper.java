package com.volunteer.web.dao;

import com.volunteer.model.UserPower;
import com.volunteer.model.UserPowerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPowerMapper {
    int countByExample(UserPowerExample example);

    int deleteByExample(UserPowerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserPower record);

    int insertSelective(UserPower record);

    List<UserPower> selectByExample(UserPowerExample example);

    UserPower selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserPower record, @Param("example") UserPowerExample example);

    int updateByExample(@Param("record") UserPower record, @Param("example") UserPowerExample example);

    int updateByPrimaryKeySelective(UserPower record);

    int updateByPrimaryKey(UserPower record);
}