package com.volunteer.web.controller.function;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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