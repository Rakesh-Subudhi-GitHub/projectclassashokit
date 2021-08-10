package com.rk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rk.binding.LoginForm;
import com.rk.service.IUserService;

@RestController
public class LoginRestController {

	@Autowired
	private IUserService userService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm)
	{
		String loginCheck = userService.loginCheck(loginForm);
		return loginCheck;
		
	}//method
	
}//class
