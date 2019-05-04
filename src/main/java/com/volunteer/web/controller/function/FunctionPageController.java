package com.volunteer.web.controller.function;

import com.volunteer.constant.UserConstant;
import com.volunteer.model.UserInfo;
import com.volunteer.model.UserPower;
import com.volunteer.web.manager.FunctionPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yuan
 * @date 2019年5月1日15:08:52
 * @desc 登录后的跳转页面
 */
@Controller
public class FunctionPageController {

    @Autowired
    private FunctionPageManager functionPageManager;

    /**
     * 跳转至登录页面
     * @return
     */
    @PostMapping(value = "/function.json")
    @ResponseBody
    public List<UserPower> login(HttpServletRequest request,
                                 HttpServletResponse response){
        UserInfo attribute = (UserInfo)request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
        List<UserPower> userPowerByRoleId = functionPageManager.findUserPowerByRoleId(attribute.getRoleId());
        return userPowerByRoleId;
    }

}
