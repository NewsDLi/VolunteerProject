package com.volunteer.web.manager;

import com.feilong.core.Validator;
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
		List<Long> powerId = CollectionsUtil.getPropertyValueList(bindRolePowers, "powerId");
		if(Validator.isNullOrEmpty(powerId)){
			return null;
		}
		UserPowerExample userPowerExample = new UserPowerExample();
		userPowerExample.createCriteria().andIdIn(powerId);
		List<UserPower> userPowers = userPowerMapper.selectByExample(userPowerExample);
		if(Validator.isNullOrEmpty(userPowers)){
			return null;
		}
		return userPowers;
	}
}
