package com.volunteer.web.manager;

import java.util.List;

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

}
