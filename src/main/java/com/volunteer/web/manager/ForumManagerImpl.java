package com.volunteer.web.manager;

import com.feilong.core.DatePattern;
import com.feilong.core.Validator;
import com.feilong.core.date.DateUtil;
import com.feilong.core.util.CollectionsUtil;
import com.volunteer.model.*;
import com.volunteer.web.dao.BindRolePowerMapper;
import com.volunteer.web.dao.CommunityArticlesMapper;
import com.volunteer.web.dao.UserPowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	@Override
	public List<CommunityArticles> selectForum(Integer type) {
		CommunityArticlesExample communityArticlesExample = new CommunityArticlesExample();
		communityArticlesExample.createCriteria().andTypeEqualTo(type);
		communityArticlesExample.setOrderByClause("publication_time desc");
		List<CommunityArticles> communityArticles = communityArticlesMapper.selectByExample(communityArticlesExample);
		return communityArticles;
	}

	@Override
	public List<CommunityArticles> selectForumText(Long id) {
		CommunityArticlesExample communityArticlesExample = new CommunityArticlesExample();
		communityArticlesExample.createCriteria().andIdEqualTo(id);
		List<CommunityArticles> communityArticles = communityArticlesMapper.selectByExample(communityArticlesExample);
		return communityArticles;
	}

	@Override
	public List<ArticleMessageBoard> selectArticleList(Long id) {
		return null;
	}
}
