package com.apress.spring.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
//@RequestMapping("/user")
@Controller
public class UserController {
	
//	@RequestMapping("/index")
//	public String index() {
//		return "UserController Index! USER首页!";
//	}
//	
//	@RequestMapping("/sayhi")
//	public String sayHi() {
//		return "Hi user, how are you? 你好吗?";
//	}
//	
//
	@RequestMapping("/")
	public String root() {
		return "redirect:/default";
	}

	@RequestMapping("/default")
	public String index(HttpSession session) {
		return "default";
	}

	@RequestMapping("/user/index")
	public String userIndex() {
		return "user/index";
	}

	@RequestMapping("/user_login")
	public String login(HttpSession session) {
		return "login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	
}
