package com.volunteer.web.manager;

import com.feilong.core.Validator;
import com.feilong.core.util.CollectionsUtil;
import com.volunteer.model.*;
import com.volunteer.web.dao.BindRolePowerMapper;
import com.volunteer.web.dao.CommunityArticlesMapper;
import com.volunteer.web.dao.UserPowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumManagerImpl implements ForumManager{

	@Autowired
	private CommunityArticlesMapper communityArticlesMapper;


	@Override
	public Integer saveFroum(CommunityArticles communityArticles) {
		int insert = communityArticlesMapper.insert(communityArticles);
		return insert;
	}
}
