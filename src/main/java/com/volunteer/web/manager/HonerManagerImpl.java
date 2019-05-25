package com.volunteer.web.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.volunteer.model.Honer;
import com.volunteer.model.UserInfo;
import com.volunteer.web.dao.HonerMapper;
import com.volunteer.web.dao.UserInfoMapper;

@Service
@Transactional
public class HonerManagerImpl implements HonerManager{

	@Autowired
	private HonerMapper honerMapper;
	
	@Autowired
    private UserInfoMapper userInfoMapper;

	@Override
	public List<Honer> getMyHoner(Long id) {
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
}
