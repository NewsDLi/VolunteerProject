package com.volunteer.web.manager;

import com.volunteer.model.CommunityArticles;
import com.volunteer.model.UserPower;

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
}
