package com.volunteer.web.manager;

import com.volunteer.model.UserPower;

import java.util.List;

/**
 * @author yuan
 * @date 2019年5月1日15:08:52
 * @desc 我的页面权限显示
 */
public interface FunctionPageManager {

		/**
		 * 通过userInfo查询当前用户具体权限
		 */
		List<UserPower> findUserPowerByRoleId();
}
