package com.volunteer.web.controller.forum;

import com.feilong.core.Validator;
import com.feilong.core.util.CollectionsUtil;
import com.volunteer.constant.UserConstant;
import com.volunteer.model.ArticleMessageBoard;
import com.volunteer.model.CommunityArticles;
import com.volunteer.model.UserInfo;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
import com.volunteer.utils.ImageUtils;
import com.volunteer.model.ArticleMessageBoardCommand;
import com.volunteer.web.manager.ForumManager;
import org.apache.commons.collections4.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author yuan.shi
 * @date 2019年5月11日01:43:19
 * @desc 论坛
 */
@Controller
public class ForumController {

    private static final String baseUrl = "http://localhost:80";

    @Autowired
    private ForumManager forumManager;

    //上传后台
    @RequestMapping(value = "/uploadEditorImg", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String uploadEditorImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
        Map<String, Object> map = new HashMap<>();
        Iterator<String> iterator = mr.getFileNames();
        while (iterator.hasNext()) {
            MultipartFile multipartFile = mr.getFile(iterator.next());
            String realPath = request.getSession().getServletContext().getRealPath("/");
            String url = ImageUtils.saveImage(request, multipartFile, realPath+"/images/shequ/");
           if(Validator.isNullOrEmpty(request.getSession().getAttribute("image"))){
               request.getSession().setAttribute("image",url);
           }
            return url;
        }
        return null;
    }

    //富文本上传后台
    @RequestMapping(value = "/uploadEditor", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String uploadEditor(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "heading", required = false) String heading, @RequestParam("type") String type, @RequestParam("title") String title, @RequestParam("data") String data) {
        HttpSession session = request.getSession();
        String url = (String)session.getAttribute("image");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
        CommunityArticles communityArticles = new CommunityArticles();
        communityArticles.setContent(data);
        communityArticles.setAuthor(userInfo.getName());
        communityArticles.setTitle(title);
        communityArticles.setPublicationTime(new Date());
        communityArticles.setLifecycle(CommunityArticles.START_LIFECYCLE);
        communityArticles.setType(Integer.parseInt(type));
        communityArticles.setImage(url);
        communityArticles.setSort(0);
        if(Validator.isNotNullOrEmpty(url)){
            session.removeAttribute("image");
        }
        if (Validator.isNotNullOrEmpty(heading)) {
            communityArticles.setSubheading(heading);
        }
        Integer isSuccess = forumManager.saveFroum(communityArticles);
        return isSuccess.toString();
    }


    //获取列表
    @RequestMapping(value = "/forumList/{type}")
    public String getFormList(HttpServletRequest request, @PathVariable("type") String type) {
        if (Validator.isNullOrEmpty(type)) {
            type = "1";
        }
        List<CommunityArticles> communityArticles = forumManager.selectForum(Integer.parseInt(type));
        if (Validator.isNullOrEmpty(communityArticles)) {
            return "communityPage";
        }
        request.setAttribute("communityArticles",communityArticles);
        return "community";
    }

    //获取内容
    @RequestMapping(value = "/forum.htm", method = {RequestMethod.GET})
    public String getForm(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") String id) {
        if (Validator.isNullOrEmpty(id)) {
            String url = request.getHeader("Referer");
            return "redirect:"+url;
        }
        List<CommunityArticles> communityArticles = forumManager.selectForumText(Long.parseLong(id));
        List<ArticleMessageBoardCommand> articleMessageBoards = forumManager.selectArticleList(Long.parseLong(id));
        Map<String, List<ArticleMessageBoardCommand>> articleMessageBoardMap =  CollectionsUtil.group(articleMessageBoards, "pid", new Predicate<ArticleMessageBoardCommand>(){
            @Override
            public boolean evaluate(ArticleMessageBoardCommand articleMessage){
                return Validator.isNotNullOrEmpty(articleMessage.getMessage());
            }
        });


        request.setAttribute("communityArticle",communityArticles.get(0));
        request.setAttribute("articleMessageBoardMap",articleMessageBoardMap);

        return "forum";
    }
    //顶置
    @RequestMapping(value = "/forum/top", method = {RequestMethod.GET})
    @ResponseBody
    public ApiResponse<Object> getFormTop(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") String id, @RequestParam(value = "sort") String sort) {
        if (Validator.isNullOrEmpty(id)) {
            return ApiResponse.build(ResponseStatus.FAIL, "");
        }
        int i = forumManager.updateArticleListbySort(Long.parseLong(id),Integer.parseInt(sort));
        if(i>0){
            return ApiResponse.build(ResponseStatus.SUCCESS, i);
        }
        return ApiResponse.build(ResponseStatus.FAIL, "");
    }

    //发表评论
    @RequestMapping(value = "/forum/save", method = {RequestMethod.POST})
    @ResponseBody
    public ApiResponse<Object> saveArticleMwssageBoard(HttpServletRequest request, HttpServletResponse response,@RequestBody ArticleMessageBoard articleMessageBoard){
        if(Validator.isNullOrEmpty(articleMessageBoard)){
                return ApiResponse.build(ResponseStatus.FAIL, "");
        }
        articleMessageBoard.setVersion(new Date());
        articleMessageBoard.setSort(0);
        int i = forumManager.saveArticleList(articleMessageBoard);
        if(i>0){
            return ApiResponse.build(ResponseStatus.SUCCESS, i);
        }
        return ApiResponse.build(ResponseStatus.FAIL, "");
    }
}