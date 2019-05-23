package com.volunteer.web.dao;

import com.volunteer.model.Honer;
import com.volunteer.model.HonerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HonerMapper {
    int countByExample(HonerExample example);

    int deleteByExample(HonerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Honer record);

    int insertSelective(Honer record);

    List<Honer> selectByExample(HonerExample example);

    Honer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Honer record, @Param("example") HonerExample example);

    int updateByExample(@Param("record") Honer record, @Param("example") HonerExample example);

    int updateByPrimaryKeySelective(Honer record);

    int updateByPrimaryKey(Honer record);

	List<Honer> getAllHoner();
}