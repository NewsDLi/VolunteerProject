package com.volunteer.web.manager;

import com.volunteer.model.*;
import com.volunteer.web.dao.ArticleMessageBoardMapper;
import com.volunteer.web.dao.CommunityArticlesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumManagerImpl implements ForumManager{

	@Autowired
	private CommunityArticlesMapper communityArticlesMapper;

	@Autowired
	private ArticleMessageBoardMapper articleMessageBoardMapper;


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
		communityArticlesExample.setOrderByClause("sort desc");
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
	public List<ArticleMessageBoard> selectArticleList(Long communityArticlesId) {
		ArticleMessageBoardExample articleMessageBoardExample = new ArticleMessageBoardExample();
		articleMessageBoardExample.createCriteria().andCommunityArticlesIdEqualTo(communityArticlesId);
		List<ArticleMessageBoard> articleMessageBoards = articleMessageBoardMapper.selectByExample(articleMessageBoardExample);
		return articleMessageBoards;
	}

	@Override
	public ArticleMessageBoard saveArticleList(ArticleMessageBoard communityArticlesId) {
		articleMessageBoardMapper.insertSelective(communityArticlesId);
		return communityArticlesId;
	}

	@Override
	public int updateArticleListbySort(Long communityArticlesId) {
		CommunityArticlesExample communityArticlesExample = new CommunityArticlesExample();
		communityArticlesExample.createCriteria().andIdEqualTo(communityArticlesId);
		CommunityArticles communityArticles = new CommunityArticles();
		communityArticles.setSort(1);

		int i = communityArticlesMapper.updateByExampleSelective(communityArticles, communityArticlesExample);
		return i;
	}
}
