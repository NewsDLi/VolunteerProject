package com.volunteer.web.controller.forum;

import com.feilong.core.Validator;
import com.volunteer.constant.UserConstant;
import com.volunteer.model.CommunityArticles;
import com.volunteer.model.UserInfo;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
import com.volunteer.utils.ImageUtils;
import com.volunteer.web.manager.ForumManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            String url = ImageUtils.saveImage(request, multipartFile, "/images/");
            return url;
        }
        return null;
    }
    //富文本上传后台
    @RequestMapping(value = "/uploadEditor", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String uploadEditor(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "heading",required=false) String heading,@RequestParam("type") String type,@RequestParam("title") String title , @RequestParam("data") String data){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
        CommunityArticles communityArticles = new CommunityArticles();
        communityArticles.setContent(data);
        communityArticles.setAuthor(userInfo.getName());
        communityArticles.setTitle(title);
        communityArticles.setPublicationTime(new Date());
        communityArticles.setLifecycle(CommunityArticles.START_LIFECYCLE);
        communityArticles.setType(Integer.parseInt(type));
        if(Validator.isNotNullOrEmpty(heading)){
            communityArticles.setSubheading(heading);
        }
        Integer isSuccess = forumManager.saveFroum(communityArticles);
        return isSuccess.toString();
    }
    //获取列表
    @RequestMapping(value = "/forumList/{type}", method = {RequestMethod.GET})
    @ResponseBody
    public ApiResponse<Object> getFormList(HttpServletRequest request, HttpServletResponse response,@PathVariable("type") String type){
        if(Validator.isNullOrEmpty(type)){
            type = "1";
        }
        List<CommunityArticles> communityArticles = forumManager.selectForum(Integer.parseInt(type));
        if(Validator.isNullOrEmpty(communityArticles)){
            return  ApiResponse.build(ResponseStatus.FAIL,null);
        }
        return ApiResponse.build(ResponseStatus.SUCCESS, communityArticles);
    }
}