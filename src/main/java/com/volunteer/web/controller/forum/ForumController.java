package com.volunteer.web.controller.forum;

import com.volunteer.utils.ImageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yuan.shi
 * @date 2019年5月11日01:43:19
 * @desc 论坛
 */
@Controller
public class ForumController {

    private static final String baseUrl = "http://localhost:80";


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
    public String uploadEditor(HttpServletRequest request, HttpServletResponse response, @RequestParam("data") String data){
        if(data.length()>1){
            return "success";
        }
        return "faile";
    }
}