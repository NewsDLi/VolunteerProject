package com.volunteer.web.manager;

import java.util.List;

import com.volunteer.model.WheelPlanting;
import com.volunteer.model.WhellPlanting;

public interface WheelPlantingManager {

	List<WhellPlanting> queryAll();

	WhellPlanting queryById(Long id);

	Integer updateWheelPlanting(WheelPlanting wheelPlanting);

}
