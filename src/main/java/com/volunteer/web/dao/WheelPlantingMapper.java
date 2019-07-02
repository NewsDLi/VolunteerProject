package com.volunteer.web.dao;

import com.volunteer.model.WhellPlanting;
import com.volunteer.model.WheelPlanting;
import com.volunteer.model.WheelPlantingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WheelPlantingMapper {
	int countByExample(WheelPlantingExample example);

	int deleteByExample(WheelPlantingExample example);

	int deleteByPrimaryKey(Long id);

	int insert(WheelPlanting record);

	int insertSelective(WheelPlanting record);

	List<WheelPlanting> selectByExample(WheelPlantingExample example);

	WheelPlanting selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") WheelPlanting record, @Param("example") WheelPlantingExample example);

	int updateByExample(@Param("record") WheelPlanting record, @Param("example") WheelPlantingExample example);

	int updateByPrimaryKeySelective(WheelPlanting record);

	int updateByPrimaryKey(WheelPlanting record);

	List<WhellPlanting> queryAll();

	WhellPlanting queryById(Long id);
}