package com.bookreccs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	// The difference between @RequestMapping and @GetMapping - @GetMapping is newer and serves as a shortcut for @RequestMapping
	// where you don't have to define for @RequestMapping(method = REQUESTMETHOD.GET) the type of request method 
	
	// @RequestMapping("bookreccs") also works in the same way as /bookreccs/login vs no RequestMapping for /login 
	
	@GetMapping("/login")
	public String showLoginPage()
	{
		return "login-page";
	}
}
