package com.rk.service;

import java.util.Map;

import com.rk.binding.LoginForm;
import com.rk.binding.UnlockAccountForm;
import com.rk.binding.UserRegForm;

public interface IUserService {

	public String loginCheck(LoginForm loginForm);
	
	public Map<Integer,String> getContries();
	public Map<Integer,String> getState(Integer countrieId);
	public Map<Integer,String> getCity(Integer stateId);
	public String emailCheck(String emailid);
	public boolean saveUser(UserRegForm userForm);
	
	public boolean unlockAccount(UnlockAccountForm unlockAcc);
	public boolean forgetPwd(String emailId);
	
}
