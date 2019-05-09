package com.volunteer.web.manager;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feilong.core.Validator;
import com.volunteer.common.WechatMessage;
import com.volunteer.model.PageNation;
import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoBind;
import com.volunteer.model.UserInfoExample;
import com.volunteer.model.WechatInfo;
import com.volunteer.model.WechatInfoExample;
import com.volunteer.web.dao.UserInfoMapper;
import com.volunteer.web.dao.WechatInfoMapper;

@Service
public class UserInfoManagerImpl implements UserInfoManager{

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private WechatInfoMapper wechatInfoMapper;

	@Autowired
	private WechatInfoManager wechatInfoManager;

	@Autowired
	private UserInfoBindManager userInfoBindManager;

	@Override
	public UserInfo getUserInfoByOpenId(WechatMessage wechatMessage) {
		List<WechatInfo> wechatInfos = getWechatInfoByOpenId(wechatMessage);
		if(Validator.isNullOrEmpty(wechatInfos)){
			wechatInfoManager.saveWechatInfo(wechatMessage);
		}
		List<UserInfoBind> userInfoBinds = userInfoBindManager.selectUserInfoBind(wechatInfos.get(0).getId());
		if(Validator.isNullOrEmpty(userInfoBinds)){
			return null;
		}
		return selectByPrimaryKey(userInfoBinds.get(0).getUserId());
	}

	@Override
	public List<UserInfo> getUserInfoByMobile(String mobile) {
		UserInfoExample userInfoExample = new UserInfoExample();
		userInfoExample.createCriteria().andLoginPhoneEqualTo(mobile);
		List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
		return userInfos;
	}

	@Override
	public Long insertUserInfo(UserInfo user) {
		int insert = userInfoMapper.insert(user);
		if(insert != 1){
			return null;
		}
		return user.getId();
	}

	@Override
	public Integer checkUserIsExist(String idCard) {
//		return userInfoMapper.checkUserIsExist(idCard);
		return  null;
	}
	@Override
	public List<WechatInfo> getWechatInfoByOpenId(WechatMessage wechatMessage) {
		WechatInfoExample wechatInfoExample = new WechatInfoExample();
		WechatInfoExample.Criteria criteria = wechatInfoExample.createCriteria().andOpenIdEqualTo(wechatMessage.getOpenId());
		List<WechatInfo> wechatInfos = wechatInfoMapper.selectByExample(wechatInfoExample);
		return wechatInfos;
	}

	@Override
	public Integer updateUserIfoToDescption(Long id, String descption,String hobby) {
		UserInfoExample userInfoExample = new UserInfoExample();
		userInfoExample.createCriteria().andIdCardEqualTo(id.toString());
		UserInfo userInfo = new UserInfo();
		if(!StringUtils.isBlank(descption)){
			userInfo.setDescption(descption);
		}
		if(!StringUtils.isBlank(hobby)){
			userInfo.setHobby(hobby);
		}
		userInfo.setDescption(descption);
		int exampleSelective = userInfoMapper.updateByExampleSelective(userInfo, userInfoExample);
		return exampleSelective;
	}

	@Override
	public UserInfo selectByPrimaryKey(Long id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int getCount(String kewWords, Integer groupteam, Long roles) {
		PageNation pageNation = new PageNation(kewWords, groupteam, roles);
		return userInfoMapper.getCount(pageNation);
	}

	@Override
	public List<UserInfo> searchInfos(String kewWords, Integer groupteam, Long roles, int begin,int pageSize) {
		PageNation pageNation = new PageNation(kewWords, groupteam, roles, begin, pageSize);
		return userInfoMapper.searchInfos(pageNation);
	}

	@Override
	public List<Integer> getAllGroups() {
		return userInfoMapper.getAllGroups();
	}

}