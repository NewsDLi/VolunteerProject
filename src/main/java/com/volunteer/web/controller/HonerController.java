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
	
	@RequestMapping("/managerhoner")
	public String toManagerHoner(HttpServletRequest request, Model model){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserConstant.LOGIN_PHONE);
		if (!userInfo.getRoleId().equals(3L)){
			return "redirect:/login.json";
		}
		List<Honer> list = honerManager.getMyHoner(userInfo.getId());
		int honerListSize = list.size();
		int size = (honerListSize / 3) + ((honerListSize % 3) == 0 ? 0 : 1);
		if (size == 0){
			return "managerhoner";
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
		
		model.addAttribute("myHoner", object);
		return "managerhoner";
	}
	
	
	
}
