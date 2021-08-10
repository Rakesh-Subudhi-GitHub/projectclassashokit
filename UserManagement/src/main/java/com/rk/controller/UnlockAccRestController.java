package com.rk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rk.binding.UnlockAccountForm;
import com.rk.service.IUserService;

@RestController
public class UnlockAccRestController {

	@Autowired
	private IUserService userService;
	
	@PostMapping("/unlockAcc")
	public String unlockAcc(@RequestBody UnlockAccountForm unlockAccForm) {
		
		boolean status = userService.unlockAccount(unlockAccForm);
		
		if(status){
		      return "Account is Unlock SuccessFully";
		}
		else
		{
			return "Invalied Temporary Password !! try OnceAgain ";
		}
	}//method
	
}//class
