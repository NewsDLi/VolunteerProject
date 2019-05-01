package com.volunteer.web.controller.function;

import com.alibaba.fastjson.JSON;
import com.feilong.core.Validator;
import com.feilong.servlet.http.RequestUtil;
import com.volunteer.cache.manager.CacheManager;
import com.volunteer.common.UserInfoBindCommand;
import com.volunteer.common.WechatMessage;
import com.volunteer.constant.UserConstant;
import com.volunteer.constant.WxLoginConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.utils.HttpsUtils;
import com.volunteer.utils.PropBean;
import com.volunteer.web.controller.login.handler.WeChatLoginHandler;
import com.volunteer.web.manager.UserInfoBindManager;
import com.volunteer.web.manager.UserInfoManager;
import com.volunteer.web.manager.WechatInfoManager;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author yuan
 * @date 2019年5月1日15:08:52
 * @desc 登录后的跳转页面
 */
@Controller
public class FunctionPageController {


    /**
     * 跳转至登录页面
     * @return
     */
    @PostMapping(value = "/index")
    public String login(HttpServletRequest request,
                        HttpServletResponse response){

        return "index";
    }

}
