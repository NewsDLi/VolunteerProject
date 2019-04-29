package com.volunteer.web.dao;

import com.volunteer.model.BindRolePower;
import com.volunteer.model.BindRolePowerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BindRolePowerMapper {
    int countByExample(BindRolePowerExample example);

    int deleteByExample(BindRolePowerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BindRolePower record);

    int insertSelective(BindRolePower record);

    List<BindRolePower> selectByExampleWithBLOBs(BindRolePowerExample example);

    List<BindRolePower> selectByExample(BindRolePowerExample example);

    BindRolePower selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BindRolePower record, @Param("example") BindRolePowerExample example);

    int updateByExampleWithBLOBs(@Param("record") BindRolePower record, @Param("example") BindRolePowerExample example);

    int updateByExample(@Param("record") BindRolePower record, @Param("example") BindRolePowerExample example);

    int updateByPrimaryKeySelective(BindRolePower record);

    int updateByPrimaryKeyWithBLOBs(BindRolePower record);

    int updateByPrimaryKey(BindRolePower record);
}