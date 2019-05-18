package com.volunteer.web.dao;

import java.util.List;

import com.volunteer.model.WhellPlanting;

public interface WheelPlantingMapper {

	List<WhellPlanting> queryAll();

	WhellPlanting queryById(Long id);

}
