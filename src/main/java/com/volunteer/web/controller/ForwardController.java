package com.volunteer.web.controller;

import com.feilong.core.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author NewsDLee
 * @date 2019年4月21日01:43:19
 * @desc 用来做页面跳转的controller
 */
@Controller
public class ForwardController {

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
    public String mypage() {
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

    /**
     * 勋章墙
     *
     * @return
     */
    @RequestMapping("/honer.htm")
    public String honer() {
        return "honer";
    }

    @RequestMapping("/weui.htm")
    public String weui() {
        return "weui";
    }

    /**
     * 主页
     */
    @RequestMapping("/homepage.htm")
    public String home() {
        return "homepage";
    }

    /**
     * 社区
     */
    @RequestMapping("/community.htm")
    public String community() {
        return "community";
    }

    /**
     * 论坛
     */
    @RequestMapping("/forum.htm")
    public String forum() {
        return "forum";
    }

    /**
     * 权限管理页面
     */
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    /**
     * 权限管理页面
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

}

