package com.volunteer.web.manager;

import com.volunteer.model.ArticleMessageBoard;
import com.volunteer.model.CommunityArticles;

import java.util.List;

/**
 * @author yuan
 * @date 2019年5月12日15:08:52
 * @desc 论坛增删改查
 */
public interface ForumManager {

	/**
	 * 保存论坛信息
	 */
	Integer saveFroum(CommunityArticles communityArticles);

	/**
	 * 查询论坛列表
	 */
	List<CommunityArticles> selectForum(Integer type);

	/**
	 * 查询论坛内容
	 */
	List<CommunityArticles> selectForumText(Long id);
	/**
	 * 查询留言内容
	 */
	List<ArticleMessageBoard> selectArticleList(Long communityArticlesId);

	/**
	 * 保存留言内容
	 */
	ArticleMessageBoard saveArticleList(ArticleMessageBoard communityArticlesId);

	/**
	 * 更新论坛排序
	 */
	int updateArticleListbySort(Long communityArticlesId);
}
