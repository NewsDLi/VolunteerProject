package com.volunteer.web.manager;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.volunteer.constant.CommonConstant;
import com.volunteer.model.Honer;
import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoTag;
import com.volunteer.utils.ImageUtils;
import com.volunteer.web.dao.HonerMapper;
import com.volunteer.web.dao.UserInfoMapper;

@Service
@Transactional
public class HonerManagerImpl implements HonerManager{
	
	private final static Logger logger = LoggerFactory.getLogger(HonerManagerImpl.class);
	
	@Autowired
	private HonerMapper honerMapper;
	
	@Autowired
    private UserInfoMapper userInfoMapper;
	
	@Autowired
	private UserInfoManager userInfoManager;
	
	@Autowired
	private UserInfoTagManager userInfoTagManager;

	@Override
	public List<Honer> getHoner(Long id) {
		UserInfo userInfoById = userInfoMapper.selectByPrimaryKey(id);
		String honerId = userInfoById.getHonerId();
		List<Honer> list = getAllHoner();
		if (StringUtils.isBlank(honerId)){
			return list;
		}
		String[] split = honerId.split(",");
		for (String string : split) {
			for (Honer honer : list) {
				if(String.valueOf(honer.getId()).equals(string)){
					honer.setIsLight(true);
					break;
				}
			}
		}
		return list;
	}
	
	@Override
	public List<Honer> getAllHoner() {
		return honerMapper.getAllHoner();
	}

	@Override
	public Honer getHonerById(Long id) {
		return honerMapper.selectByPrimaryKey(id);
	}

	@Override
	public Long save(Honer honer) {
		if (null == honer.getId()){
			honerMapper.insertSelective(honer);
			return honer.getId();
		}
		honerMapper.updateByPrimaryKeySelective(honer);
		return honer.getId();
	}

	@Override
	public void updateUserHonerInfo(Long id){
		try{
			UserInfo userInfoById = userInfoManager.getUserInfoById(id);
			List<UserInfoTag> list = userInfoTagManager.getUserCareer(id);
			if (list.size()<=0){
				return;
			}
			List<Honer> allHoner = getAllHoner();
			Integer count = 0;
			for (UserInfoTag userInfoTag : list) {
				if(CommonConstant.TYPE_CLASS.equals(userInfoTag.getType())){
					count += userInfoTag.getTagCount();
					continue;
				}
			}
			String honerId = userInfoById.getHonerId();
			List<String> asList = null;
			if(StringUtils.isNotBlank(honerId)){
				asList = Arrays.asList(honerId.split(","));
			} else {
				honerId = "";
			}
			for (Honer honer : allHoner) {
				Integer range = honer.getRange();
				if (null == range || honer.getIsClickSend() || (null !=asList && asList.contains(honer.getId().toString()))){
					continue;
				}
				// 义工总期数大于或等于
				if(count >= range && (honerId.indexOf(String.valueOf(honer.getId())) == -1)){
					honerId += "," + String.valueOf(honer.getId());
				}
			}
			if(honerId.startsWith(",")){
				honerId = honerId.substring(1, honerId.length());
			}
			userInfoById.setHonerId(honerId);
			boolean result = userInfoManager.updateUserInfoById(userInfoById);
		}catch (Exception e){
			logger.error("更新勋章墙出错！", e);
		}
	}
}
