package com.volunteer.web.manager;

import java.util.ArrayList;
import java.util.List;

import com.feilong.core.bean.PropertyUtil;
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
	public List<WheelPlanting> queryAll() {
		List<WheelPlanting> wheelPlantings = new ArrayList<>();
		WheelPlantingExample wheelPlantingExample = new WheelPlantingExample();
		wheelPlantings = wheelPlantingMapper.selectByExample(wheelPlantingExample);
		return wheelPlantings;
	}

	@Override
	public WheelPlanting queryById(Long id) {
		WheelPlantingExample wheelPlantingExample = new WheelPlantingExample();
		wheelPlantingExample.createCriteria().andIdEqualTo(id);
		List<WheelPlanting> wheelPlantings = wheelPlantingMapper.selectByExample(wheelPlantingExample);
		return wheelPlantings.get(0);
	}

	@Override
	public Integer updateWheelPlanting(WheelPlanting wheelPlanting) {
		WheelPlantingExample wheelPlantingExample = new WheelPlantingExample();
		wheelPlantingExample.createCriteria().andIdEqualTo(wheelPlanting.getId());
		int i = wheelPlantingMapper.updateByExample(wheelPlanting, wheelPlantingExample);
		return i;
	}

}
