package com.donggyeong.fastorganizer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.donggyeong.fastorganizer.user.UserCreateForm;

@Controller
public class MainController {

	@RequestMapping("/test/page")
	@ResponseBody
	public String testPage() {
		return "Hello world, this is a test page.";
	}
	
	/*
	@RequestMapping("/")
	public String index() {
		return "redirect:/work/list";
	}
	*/
	@RequestMapping("/")
	public String index(UserCreateForm userCreateForm) {
		return "index";
	}
}
