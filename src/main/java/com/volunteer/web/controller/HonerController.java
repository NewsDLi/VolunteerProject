package com.volunteer.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.volunteer.constant.UserConstant;
import com.volunteer.model.Honer;
import com.volunteer.model.UserInfo;
import com.volunteer.web.manager.HonerManager;

@Controller
public class HonerController {

	@Autowired
	private HonerManager honerManager;
	
	/**
     * 勋章墙
     *
     * @return
     */
    @RequestMapping("/honer.htm")
    public String honer(HttpServletRequest request, Model model) {
    	UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		List<Honer> list = honerManager.getMyHoner(userInfo.getId());
		List<Object> object = sortListHoner(list);
		if (null == object) {
			return "redirect:/login.json";
		}
		model.addAttribute("myHoner", object);
        return "honer";
    }
	
    /**
     * 管理勋章墙
     * @param request
     * @param model
     * @return
     */
	@RequestMapping("/managerhoner")
	public String toManagerHoner(HttpServletRequest request, Model model){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		if (!userInfo.getRoleId().equals(3L)){
			return "redirect:/login.json";
		}
		List<Honer> allHoner = honerManager.getAllHoner();
		List<Object> sortListHoner = sortListHoner(allHoner);
		if (null == sortListHoner) {
			return "redirect:/login.json";
		}
		model.addAttribute("myHoner", sortListHoner);
		return "managerhoner";
	}
	
	private List<Object> sortListHoner(List<Honer> list) {
		int honerListSize = list.size();
		// 默认设置三列一行
		int size = (honerListSize / 3) + ((honerListSize % 3) == 0 ? 0 : 1);
		if (size == 0){
			return null;
		}
		List<Object> object = new ArrayList<Object>();
		// 分为size组
		List<Honer> honer = null;
		for (int i=1; i<=size; i++) {
			honer = new ArrayList<Honer>(3);
			for(int j=(i-1)*3; j<i*3; j++){
				if (j >= honerListSize){
					break;
				}
				honer.add(list.get(j));
			}
			object.add(honer);
		}
		return object;
	}
	
}
