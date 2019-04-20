package com.volunteer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController {
	
	@RequestMapping("/index")
	public String toIndex(){
		return "index";
	}
}
