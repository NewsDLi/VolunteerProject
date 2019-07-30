package com.volunteer.web.manager;

import com.feilong.core.Validator;
import com.feilong.core.util.SortUtil;
import com.volunteer.model.*;
import com.volunteer.web.dao.ArticleMessageBoardMapper;
import com.volunteer.web.dao.CommunityArticlesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
		communityArticlesExample.createCriteria().andTypeEqualTo(type).andLifecycleEqualTo(1);
		communityArticlesExample.setPageIndex(100);
		List<CommunityArticles> communityArticles = communityArticlesMapper.selectByExample(communityArticlesExample);
		SortUtil.sortListByPropertyNamesValue(communityArticles, "sort desc", "publicationTime desc");
		return communityArticles;
	}

	@Override
	public List<CommunityArticles> selectHomePageForum() {
		List<CommunityArticles> communityArticlesList = new ArrayList<>();
		for (int i = 1;i<=4;i++){
			CommunityArticlesExample communityArticlesExample = new CommunityArticlesExample();
			communityArticlesExample.createCriteria().andTypeEqualTo(i).andLifecycleEqualTo(1);
			communityArticlesExample.setPageIndex(1);
			List<CommunityArticles> communityArticles = communityArticlesMapper.selectByExample(communityArticlesExample);
			SortUtil.sortListByPropertyNamesValue(communityArticles, "sort desc", "publicationTime desc");
			if(Validator.isNullOrEmpty(communityArticles)){
				continue;
			}
			communityArticlesList.add(communityArticles.get(0));
		}
		return communityArticlesList;
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
		int i = 0;
		try {

			CommunityArticlesExample communityArticlesExample = new CommunityArticlesExample();
			communityArticlesExample.createCriteria().andIdEqualTo(communityArticlesId);
			CommunityArticles communityArticles = new CommunityArticles();
			communityArticles.setSort(sort);

			i = communityArticlesMapper.updateByExampleSelective(communityArticles, communityArticlesExample);
		}catch (Exception e){
			System.out.println(e);
		}
		return i;
	}

	@Override
	public int updateForumbySort(Long id, Long communityArticleId,Integer sort) {
//		if( sort.equals(3)){
//			//教授顶置
//			ArticleMessageBoardExample articleMessageBoardExample2 = new ArticleMessageBoardExample();
//			articleMessageBoardExample2.createCriteria().andCommunityArticlesIdEqualTo(communityArticleId).andSortEqualTo(3);
//			ArticleMessageBoard articleMessageBoard2 = new ArticleMessageBoard();
//			articleMessageBoard2.setSort(1);
//			articleMessageBoardMapper.updateByExampleSelective(articleMessageBoard2,articleMessageBoardExample2);
//
//		}

		//取消旧置顶
		if(sort.equals(2)){
			ArticleMessageBoardExample articleMessageBoardExample2 = new ArticleMessageBoardExample();
			articleMessageBoardExample2.createCriteria().andCommunityArticlesIdEqualTo(communityArticleId).andSortEqualTo(2);
			ArticleMessageBoard articleMessageBoard2 = new ArticleMessageBoard();
			articleMessageBoard2.setSort(0);
			articleMessageBoardMapper.updateByExampleSelective(articleMessageBoard2,articleMessageBoardExample2);
		}
		//新的置顶
		ArticleMessageBoardExample articleMessageBoardExample = new ArticleMessageBoardExample();
		articleMessageBoardExample.createCriteria().andIdEqualTo(id);
		ArticleMessageBoard articleMessageBoard = new ArticleMessageBoard();
		articleMessageBoard.setSort(sort);
		int i = articleMessageBoardMapper.updateByExampleSelective(articleMessageBoard, articleMessageBoardExample);
		return i;

	}

	@Override
	public int deleteArticleListbySort(Long communityArticlesId) {
		CommunityArticlesExample communityArticlesExample = new CommunityArticlesExample();
		communityArticlesExample.createCriteria().andIdEqualTo(communityArticlesId);
		CommunityArticles communityArticles = new CommunityArticles();
		communityArticles.setLifecycle(2);
		int i = communityArticlesMapper.updateByExampleSelective(communityArticles, communityArticlesExample);
		return i;
	}
}
