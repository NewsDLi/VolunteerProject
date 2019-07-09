package com.volunteer.web.manager;

import com.volunteer.model.WheelPlanting;

import java.util.List;

public interface WheelPlantingManager {

	List<WheelPlanting> queryAll();

	WheelPlanting queryById(Long id);

	Integer updateWheelPlanting(WheelPlanting wheelPlanting);

}
