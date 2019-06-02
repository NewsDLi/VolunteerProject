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
	public List<ArticleMessageBoardCommand> selectArticleList(Long communityArticlesId) {
		List<ArticleMessageBoardCommand> articleMessageBoardCommands = articleMessageBoardMapper.selectArticleMessageBoardCommand(communityArticlesId);
		return articleMessageBoardCommands;
	}

	@Override
	public int saveArticleList(ArticleMessageBoard communityArticlesId) {
		int i = articleMessageBoardMapper.insertSelective(communityArticlesId);
		if(communityArticlesId.getPid().equals(0)){
			ArticleMessageBoardExample articleMessageBoardExample = new ArticleMessageBoardExample();
			articleMessageBoardExample.createCriteria().andIdEqualTo(communityArticlesId.getId());
			ArticleMessageBoard articleMessageBoard = new ArticleMessageBoard();
			articleMessageBoard.setPid(communityArticlesId.getId().intValue());
			int i1 = articleMessageBoardMapper.updateByExampleSelective(articleMessageBoard, articleMessageBoardExample);
			return i1;
		}
		return  i ;
	}

	@Override
	public int updateArticleListbySort(Long communityArticlesId,Integer sort) {
		CommunityArticlesExample communityArticlesExample = new CommunityArticlesExample();
		communityArticlesExample.createCriteria().andIdEqualTo(communityArticlesId);
		CommunityArticles communityArticles = new CommunityArticles();
		communityArticles.setSort(sort);

		int i = communityArticlesMapper.updateByExampleSelective(communityArticles, communityArticlesExample);
		return i;
	}
}
