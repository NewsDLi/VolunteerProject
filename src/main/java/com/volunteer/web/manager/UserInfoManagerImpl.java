package com.volunteer.web.manager;

import java.util.List;

import com.volunteer.constant.WxLoginConstant;
import com.volunteer.model.*;
import com.volunteer.web.dao.WechatInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.feilong.core.Validator;
import com.volunteer.common.WechatMessage;
import com.volunteer.web.controller.login.handler.WeChatLoginHandler;
import com.volunteer.web.dao.UserInfoMapper;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class UserInfoManagerImpl implements UserInfoManager {

	private Logger LOGGER = LoggerFactory.getLogger(UserInfoManagerImpl.class);
	
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private WechatInfoMapper wechatInfoMapper;

    @Autowired
    private WechatInfoManager wechatInfoManager;

    @Autowired
    private UserInfoBindManager userInfoBindManager;

    @Override
    public UserInfo getUserInfoByOpenId(WechatInfo wechatInfo, HttpServletRequest request) {
    	LOGGER.info("通过用户传递的微信信息：{}，查询数据库微信信息。", JSON.toJSONString(wechatInfo));
        List<WechatInfo> wechatInfo1 = wechatInfoManager.findWechatInfo(wechatInfo);
        LOGGER.info("查询的微信信息结果为：{}", JSON.toJSONString(wechatInfo1));
        if (Validator.isNotNullOrEmpty(wechatInfo1)) {
        	LOGGER.info("开始查询用户信息");
        	UserInfo returnUserInfo = returnUserInfo(wechatInfo1.get(0), request);
        	LOGGER.info("查询的用户信息为：{}", JSON.toJSONString(returnUserInfo));
            return returnUserInfo;
        }
        LOGGER.info("保存用户信息");
        WechatInfo wechatInfo2 = wechatInfoManager.saveWechatInfo(wechatInfo);
        LOGGER.info("保存用户信息结果：{}", JSON.toJSONString(wechatInfo2));
        return returnUserInfo(wechatInfo, request);

    }

    private UserInfo returnUserInfo(WechatInfo wechatInfo, HttpServletRequest request) {
        request.getSession().setAttribute(WxLoginConstant.WECHAT_USERINFO_SESSION, wechatInfo);
        List<UserInfoBind> userInfoBinds = userInfoBindManager.selectUserInfoBind(wechatInfo.getId());
        if (Validator.isNullOrEmpty(userInfoBinds)) {
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
        if (insert != 1) {
            return null;
        }
        return user.getId();
    }

    @Override
    public Integer checkUserIsExist(String idCard) {
		return userInfoMapper.checkUserIsExist(idCard);
    }

    @Override
    public List<WechatInfo> getWechatInfoByOpenId(WechatMessage wechatMessage) {
        WechatInfoExample wechatInfoExample = new WechatInfoExample();
        WechatInfoExample.Criteria criteria = wechatInfoExample.createCriteria().andOpenIdEqualTo(wechatMessage.getOpenId());
        List<WechatInfo> wechatInfos = wechatInfoMapper.selectByExample(wechatInfoExample);
        return wechatInfos;
    }

    @Override
    public Integer updateUserIfoToDescption(Long id, String descption, String hobby) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andIdCardEqualTo(id.toString());
        UserInfo userInfo = new UserInfo();
        if (!StringUtils.isBlank(descption)) {
            userInfo.setDescption(descption);
        }
        if (!StringUtils.isBlank(hobby)) {
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
    public List<UserInfo> searchInfos(String kewWords, Integer groupteam, Long roles, int begin, int pageSize) {
        PageNation pageNation = new PageNation(kewWords, groupteam, roles, begin, pageSize);
        return userInfoMapper.searchInfos(pageNation);
    }

    @Override
    public List<Integer> getAllGroups() {
        return userInfoMapper.getAllGroups();
    }

    @Override
    public UserInfo getUserInfoById(Long id) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andIdEqualTo(id).andLifecycleEqualTo(1);
        return userInfoMapper.selectByExample(userInfoExample).get(0);
    }

    @Override
    public boolean updateUserInfoById(UserInfo userInfo) {
        int result = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (result == 1) {
            return true;
        }
        return false;
    }

	@Override
	public List<UserInfo> getUsers(Integer group) {
		if(null != group){
			UserInfoExample userInfoExample = new UserInfoExample();
			userInfoExample.createCriteria().andGroupTeamEqualTo(group);
			return userInfoMapper.selectByExample(userInfoExample);
		}
		return userInfoMapper.getAllUser();
	}

}