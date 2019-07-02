package com.volunteer.web.manager;

import java.util.List;

import com.volunteer.model.WheelPlanting;
import com.volunteer.model.WheelPlantingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.volunteer.model.WhellPlanting;
import com.volunteer.web.dao.WheelPlantingMapper;

@Service
@Transactional
public class WheelPlantingManagerImpl implements WheelPlantingManager{

	@Autowired
	private WheelPlantingMapper wheelPlantingMapper;
	
	@Override
	public List<WhellPlanting> queryAll() {
		return wheelPlantingMapper.queryAll();
	}

	@Override
	public WhellPlanting queryById(Long id) {
		return wheelPlantingMapper.queryById(id);
	}

	@Override
	public Integer updateWheelPlanting(WheelPlanting wheelPlanting) {
		WheelPlantingExample wheelPlantingExample = new WheelPlantingExample();
		wheelPlantingExample.createCriteria().andIdEqualTo(wheelPlanting.getId());
		int i = wheelPlantingMapper.updateByExample(wheelPlanting, wheelPlantingExample);
		return i;
	}

}
