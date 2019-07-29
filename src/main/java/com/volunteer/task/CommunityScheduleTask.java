package com.volunteer.task;


import com.feilong.core.Validator;
import com.volunteer.model.ArticleMessageBoard;
import com.volunteer.model.CommunityArticles;
import com.volunteer.model.CommunityArticlesExample;
import com.volunteer.web.dao.ArticleMessageBoardMapper;
import com.volunteer.web.dao.CommunityArticlesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class CommunityScheduleTask {

    @Autowired
    private ArticleMessageBoardMapper articleMessageBoardMapper;

    @Autowired
    private CommunityArticlesMapper communityArticlesMapper;


    //3.添加定时任务
    @Scheduled(cron = "0 0 0/2 * * ?")
    private void configureTasks() {
        for (int i = 1;i<5;i++){
            //查询最新热帖
            ArticleMessageBoard articleMessageBoard = articleMessageBoardMapper.selectArticleMessageBoardTop(i);
            if(Validator.isNullOrEmpty(articleMessageBoard)){
                continue;
            }
            //查询当前热帖
            CommunityArticlesExample communityArticlesExample1 = new CommunityArticlesExample();
            communityArticlesExample1.createCriteria().andTypeEqualTo(i).andSortEqualTo(1);
            List<CommunityArticles> communityArticlesList = communityArticlesMapper.selectByExample(communityArticlesExample1);
            if(Validator.isNullOrEmpty(communityArticlesList)){
                continue;
            }
            if(communityArticlesList.get(0).getId().equals(articleMessageBoard.getCommunityArticlesId())){
                continue;
            }
            CommunityArticlesExample communityArticlesExample2 = new CommunityArticlesExample();
            communityArticlesExample2.createCriteria().andIdEqualTo(communityArticlesList.get(0).getId());
            CommunityArticles communityArticles1 = new CommunityArticles();
            communityArticles1.setSort(0);
            int i1 = communityArticlesMapper.updateByExampleSelective(communityArticles1, communityArticlesExample2);
            if(i1==0){
                continue;
            }
            CommunityArticlesExample communityArticlesExample3 = new CommunityArticlesExample();
            communityArticlesExample3.createCriteria().andIdEqualTo(articleMessageBoard.getCommunityArticlesId());
            CommunityArticles communityArticles2 = new CommunityArticles();
            communityArticles2.setSort(1);
            communityArticlesMapper.updateByExampleSelective(communityArticles2,communityArticlesExample3);
        }

    }
}
