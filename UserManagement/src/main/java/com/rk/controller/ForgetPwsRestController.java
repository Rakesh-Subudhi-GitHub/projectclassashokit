package com.rk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rk.service.IUserService;

@RestController
public class ForgetPwsRestController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/forgetPwd/{emailId}")
	public String forgetPassword(@PathVariable String emailId)
	{
		boolean status = userService.forgetPwd(emailId);
		
		if(status)
		{
		return "WE HAVE TO SENT YOUR PASSWORD TO YOUR MAIL ID ..."	;
		}
		else {
		return "please enter valid email id";	
		}
		
	}//method
}//class
