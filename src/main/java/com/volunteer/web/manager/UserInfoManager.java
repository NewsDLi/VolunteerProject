package com.volunteer.web.manager;

import java.util.List;

import com.volunteer.common.WechatMessage;
import com.volunteer.model.UserInfo;
import com.volunteer.model.WechatInfo;

import javax.servlet.http.HttpServletRequest;

public interface UserInfoManager {
	/**
	 *通过openid查询用户信息
	 */
	UserInfo getUserInfoByOpenId(WechatInfo wechatInfo, HttpServletRequest request);

	/**
	 *通过openid查询微信信息
	 */
	List<WechatInfo> getWechatInfoByOpenId(WechatMessage wechatMessage);
	
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
	 * @return
	 */
	List<UserInfo> searchInfos(String kewWords, Integer groupteam, Long roles, int begin, int pagesize);

	/**
	 * 查询所有的组别
	 * @return
	 */
	List<Integer> getAllGroups();

	/**
	 * 根据用户id获取用户信息
	 * @param id
	 * @return
	 */
	UserInfo getUserInfoById(Long id);

	/**
	 * 更新会员信息
	 * @param userInfo
	 * @return
	 */
	boolean updateUserInfoById(UserInfo userInfo);

}
