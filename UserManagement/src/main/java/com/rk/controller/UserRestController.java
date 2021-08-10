package com.rk.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rk.binding.UserRegForm;
import com.rk.constants.AppConstants;
import com.rk.props.AppProps;
import com.rk.service.IUserService;

@RestController
public class UserRestController {

	private IUserService userService;

	@Autowired
	public UserRestController(IUserService userService) {
		this.userService = userService;
	}//constructor 
	
	@Autowired
	private AppProps appProp;
	
	@GetMapping("/contries")
	public Map<Integer, String> getContries()
	{
		return userService.getContries();
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer,String> getState(@PathVariable Integer countryId){
		
		return userService.getState(countryId);
	}

	@GetMapping("/citys/{stateId}")
	public Map<Integer,String> getCitys(@PathVariable Integer stateId){
		
		return userService.getCity(stateId);
	}
	
	@GetMapping("/emailcheck/{email}")
	public String uniqeEmailCheck(@PathVariable String email)
	{
		String status = userService.emailCheck(email);
		return status;
	}
	
	@PostMapping("/saveUser")
	public String saveUserRecord(@RequestBody UserRegForm userForm) {
		
		Map<String, String> messages = appProp.getMessages();
		
		boolean check = userService.saveUser(userForm);
		
		if(check) {
			//return "Successfully register";
			return messages.get(AppConstants.USER_REG_SUCCESS);
		}
		else {
			return messages.get(AppConstants.USER_REG_FAIELD);
		}
		
	}//method

}//class
