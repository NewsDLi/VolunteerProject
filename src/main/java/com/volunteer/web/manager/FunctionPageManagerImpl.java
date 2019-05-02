package com.volunteer.web.manager;

import com.feilong.core.bean.PropertyUtil;
import com.feilong.core.util.CollectionsUtil;
import com.volunteer.model.BindRolePower;
import com.volunteer.model.BindRolePowerExample;
import com.volunteer.model.UserPower;
import com.volunteer.model.UserPowerExample;
import com.volunteer.web.dao.BindRolePowerMapper;
import com.volunteer.web.dao.UserPowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionPageManagerImpl implements FunctionPageManager{


	@Autowired
	private UserPowerMapper userPowerMapper;

	@Autowired
	private BindRolePowerMapper bindRolePowerMapper	;

	@Override
	public List<UserPower> findUserPowerByRoleId(Long roleId) {

		BindRolePowerExample bindRolePowerExample = new BindRolePowerExample();
		bindRolePowerExample.createCriteria().andRoleIdEqualTo(roleId);
		List<BindRolePower> bindRolePowers = bindRolePowerMapper.selectByExample(bindRolePowerExample);
//		CollectionsUtil.find(bindRolePowers,"powerId",)
//		bindRolePowers
		UserPowerExample userPowerExample = new UserPowerExample();
//		userPowerExample.createCriteria().andIdIn()
//		List<UserPower> userPowers = userPowerMapper.selectByExample();
		return null;
	}
}
