package com.volunteer.web.controller;

import com.feilong.core.Validator;
import com.volunteer.constant.UserConstant;
import com.volunteer.constant.WxLoginConstant;
import com.volunteer.model.CommunityArticles;
import com.volunteer.model.UserInfo;
import com.volunteer.model.WechatInfo;
import com.volunteer.model.WheelPlanting;
import com.volunteer.web.manager.ForumManager;
import com.volunteer.web.manager.WheelPlantingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Enumeration;
import java.util.List;

/**
 * @author NewsDLee
 * @date 2019年4月21日01:43:19
 * @desc 用来做页面跳转的controller
 */
@Controller
public class ForwardController {

    @Autowired
    private ForumManager forumManager;

    @Autowired
    private WheelPlantingManager wheelPlantingManager;


    /**
     * 跳转至首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    /**
     * 跳转至微信登录页面
     *
     * @return
     */
    @RequestMapping("/wechartLogin")
    public String toWechartLogin() {
        return "wechartLogin";
    }

    /**
     * 跳转至微信登录页面
     *
     * @return
     */
    @RequestMapping("/mypage")
    public String mypage(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
        WechatInfo wechatInfo = (WechatInfo)request.getSession().getAttribute(WxLoginConstant.WECHAT_USERINFO_SESSION);
        if(Validator.isNullOrEmpty(userInfo)&&Validator.isNotNullOrEmpty(wechatInfo)){
            return "redirect:/homepage.htm";
        }
        return "mypage";
    }

    /**
     * 跳转至个人信息
     *
     * @return
     */
    @RequestMapping("/myinfo")
    public String myInfo() {
        return "myinfo";
    }

    /**
     * 跳转至义工生涯
     *
     * @return
     */
    @RequestMapping("/mycareer")
    public String mycareer() {
        return "mycareer";
    }

    /**
     * 跳转至文件上传
     *
     * @return
     */
    @RequestMapping("/import.htm")
    public String upload() {
        return "import";
    }

    /**
     * 跳转留言板页面
     *
     * @return
     */
    @RequestMapping("/messageBoard")
    public String messageBoard() {
        return "messageBoard";
    }


    @RequestMapping("/weui.htm")
    public String weui() {
        return "weui";
    }

    // 游客登录第一件事清空所有session
    @RequestMapping("/tourist/visit")
    public String tourist(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	Enumeration em = session.getAttributeNames();
		while(em.hasMoreElements()){
		   session.removeAttribute(em.nextElement().toString());
		}
        return "redirect:/homepage.htm";
    }
    
    /**
     * 主页
     */
    @RequestMapping("/homepage.htm")
    public String home(HttpServletRequest request) {
        List<CommunityArticles> communityArticlesList = forumManager.selectHomePageForum();
        if(Validator.isNotNullOrEmpty(communityArticlesList)){
            request.setAttribute("communityArticles",communityArticlesList);
        }
        List<WheelPlanting> wheelPlantings = wheelPlantingManager.queryAll();
        if(Validator.isNotNullOrEmpty(wheelPlantings)){
            request.setAttribute("wheelPlantings",wheelPlantings);
        }
        return "homepage";
    }

    /**
     * 社区主页
     */
    @RequestMapping("/communityPage.htm")
    public String communityPage() {
        return "communityPage";
    }
    /**
     * 社区
     */
    @RequestMapping("/community.htm")
    public String community(HttpServletRequest request,@RequestParam Integer type) {
        List<CommunityArticles> communityArticles = forumManager.selectForum(type);
        request.setAttribute("communityArticles",communityArticles);
        request.setAttribute("type",type);
        return "community";
    }

    /**

    /**
     * 义工组别
     */
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
    /**
     * 权限管理
     */
    @RequestMapping("/permission.htm")
    public String permission() {
    	return "permission";
    }
    
    
    /**
     * 论坛编辑
     */
    @RequestMapping("/editor")
    public String editor() {
        return "editor";
    }

    /**
     * 微信回调
     */
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public String getWxUserInfo(HttpServletRequest request, @RequestParam(required = true) String echostr) {
        try {
            if(Validator.isNullOrEmpty(echostr)){

                echostr = "wechatToken";
                return echostr;
            }
            //只需要把微信请求的 echostr, 返回给微信就可以了
            return echostr;
        } catch (Exception e) {
            return "falie";
        }
    }
    
    /**
     * 上传头像页面
     */
    @RequestMapping("/uploadHeadImg")
    public String uploadHeadImg() {
        return "uploadImg";
    }

}
