package com.volunteer.web.manager;

import java.util.List;

import com.volunteer.common.UserInfoBindCommand;
import com.volunteer.model.UserInfo;

public interface UserInfoManager {
	/**
	 *通过openid查询用户信息
	 */
	UserInfoBindCommand getUserInfoByOpenId(String openId);

	/**
	 *通过手机号码查询用户信息
	 */
	List<UserInfo> getUserInfoByMobile(String mobile);

	/**
	 * 插入用户信息，并返回主键
	 * @param user
	 * @return
	 */
	Long insertUserInfo(UserInfo user);

	/**
	 * 通过身份证号，查询用户是否存在
	 * @param idCard
	 * @return
	 */
	Integer checkUserIsExist(String idCard);

	/**
	 * 更据用户id修改个人介绍以及爱好
	 * @param id
	 * @return
	 */
	Integer updateUserIfoToDescption(Long id,String descption,String hobby);
	
	/**
	 * 根据主键id查询用户信息
	 * @param id
	 * @return
	 */
	UserInfo selectByPrimaryKey(Long id);

	/**
	 * 查询用户总数量
	 * @return
	 */
	int getCount(String kewWords, Integer groupteam, Long roles);

	/**
	 * 分页查询
	 * @param kewWords 关键字
	 * @param groupteam 组别
	 * @param roles 角色
	 * @param i 开始查询的条数
	 * @return
	 */
	List<UserInfo> searchInfos(String kewWords, Integer groupteam, Long roles, int begin, int pagesize);

}
