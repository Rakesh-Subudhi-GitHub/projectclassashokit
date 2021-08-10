package com.rk.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.rk.binding.LoginForm;
import com.rk.binding.UnlockAccountForm;
import com.rk.binding.UserRegForm;
import com.rk.constants.AppConstants;
import com.rk.entity.CityEntity;
import com.rk.entity.CountriesEntity;
import com.rk.entity.StateEntity;
import com.rk.entity.UserRegEntity;
import com.rk.props.AppProps;
import com.rk.repository.CityRepo;
import com.rk.repository.CountriesRepo;
import com.rk.repository.StateRepo;
import com.rk.repository.UserRepo;
import com.rk.utils.EmailUtils;
import com.rk.utils.PWDUtils;

@Service
public class UserServiceImpl implements IUserService{

	private CityRepo cityRepo;
	private CountriesRepo countryRepo;
	private StateRepo stateRepo;
	private UserRepo userRepo;
	
	@Autowired
	public UserServiceImpl(CityRepo cityRepo, CountriesRepo countryRepo, StateRepo stateRepo, UserRepo userRepo) {
		this.cityRepo = cityRepo;
		this.countryRepo = countryRepo;
		this.stateRepo = stateRepo;
		this.userRepo = userRepo;
	
	}//constructor
	
	@Autowired
	private AppProps appProp;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String loginCheck(LoginForm loginForm) {
		
		String msg=AppConstants.EMPTY_STR;
		Map<String, String> messages = appProp.getMessages();
		
		//Step-1 : check gives credentials are valid or not
		
		//Password is encrypted bcz db password is encrypted mode
		//String pwd=PWDUtils.encryptMsg(loginForm.getPassword());
		
		System.out.println("email : "+loginForm.getEmailId());
		System.out.println("password : "+loginForm.getPassword());
		
		UserRegEntity user = userRepo.findByEmailAndPassword(loginForm.getEmailId(),loginForm.getPassword());
		
		if(user !=null) {
		
				//Step-2 : If credentials are valid then check account status(locked or unlocked)
				String accStatus=user.getAccStatus();
			
				//if(accStatus.equalsIgnoreCase(messages.get(AppConstants.LOCKED))) //if account is not there it returns NULLPOINTER EXCEPTION so it bad 
				if(messages.get(AppConstants.LOCKED).equalsIgnoreCase(accStatus))	//Always take this
				{
					msg=messages.get(AppConstants.AccLocked);	
			
				}//if
				else {
				
					//Step-3 : If user account is Unlocked then user should able to login into application 
					msg=messages.get(AppConstants.SUCCESS);
				}//else
			
			}//if
		
	else {
		    	msg=appProp.getMessages().get(AppConstants.Invalid_Credentials);
	    	}//else
		
		System.out.println("msg is :: "+msg);
		return msg;
		
	}//method

	
	
	@Override
	public Map<Integer, String> getContries() {
		
		List<CountriesEntity> countries = countryRepo.findAll();
		
		Map<Integer, String> countryMap=new HashMap<>();
		
		//all countries to take map then return it simple
		countries.forEach(EachCountry ->{
			countryMap.put(EachCountry.getCountryId(),EachCountry.getCountryName());
		});
		
		return countryMap;
	}//method

	@Override
	public Map<Integer, String> getState(Integer countrieId) {
		List<StateEntity> states = stateRepo.findByCountryId(countrieId);
		
		Map<Integer, String> stateMap=new HashMap<>();
		
		states.forEach(EachState ->{
			stateMap.put(EachState.getStateId() , EachState.getStateName());
		});
		
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCity(Integer stateId) {
		
		List<CityEntity> citys = cityRepo.findByStateId(stateId);
		
		Map<Integer, String> cityMap=new HashMap<>();
		
		citys.forEach(EachCity -> {
			cityMap.put(EachCity.getCityid(), EachCity.getCityName());
		});
		
		return cityMap;
	}

	@Override
	public String emailCheck(String emailId) {
		
		Optional<UserRegEntity> findOne = getUserByEmail(emailId);
		
		if(findOne.isPresent()) {
			return AppConstants.DUPLICATE;
		}
		else {
			return AppConstants.UNIQE;
		}
		
	}//method

	
	//generate Random password
	private  static String generateRandomPassword(int length) {
		
	    StringBuilder sb = new StringBuilder();
	    
	    Random random = new Random();
	    
	    for (int i = 0; i < length; i++) {
	        sb.append(AppConstants.Candidate_Chars.charAt(
	        		                                                  random.nextInt(AppConstants.Candidate_Chars.length() ) 
	        		                                                                            ) 
	        		         );
	    }

	    return sb.toString();
	}//generate Random password

	
	
	@Override
	public boolean saveUser(UserRegForm userForm) {
		
		UserRegEntity userEntity=new UserRegEntity();
		
		BeanUtils.copyProperties(userForm, userEntity);
		
		//add extra things that not present in the UserRegform
		userEntity.setAccStatus(AppConstants.LOCKED); //by default it take locked
		
		String randomPwd=generateRandomPassword(6);
		System.out.println("TEMP PWD :: "+randomPwd);
		
		String encryptPwd = PWDUtils.encryptMsg(randomPwd);
		
		userEntity.setPassword(encryptPwd);//to take random password take 6 char
		
		//save the user
		UserRegEntity userReg = userRepo.save(userEntity);
		
		String emailBody = readUnlockAccEmailBody(userEntity);
		
		String emailSubject=appProp.getMessages().get(AppConstants.UNLOCK_ACCOUNT_SUBJECT);
		
		boolean status = emailUtils.sendEmail(userEntity.getEmail(), emailSubject, emailBody);
		
		return userReg.getUserId() !=null ?  true : false;
	
	}//method

	
	//========================================================
	
	@Override
	public boolean unlockAccount(UnlockAccountForm unlockAcc) {

		String email = unlockAcc.getEmail();
		String tempPssword = unlockAcc.getTempPssword();//send the Temppassword in random
		
		String encryPWD=PWDUtils.encryptMsg(tempPssword);
		
		//check the email and password
		 UserRegEntity user = userRepo.findByEmailAndPassword(email, encryPWD);
		
		if(user != null)
		{
			user.setPassword(unlockAcc.getNewPassword());//new password send by the user directly
			user.setAccStatus(AppConstants.UNLOCKED);
			
			//then save the user (just like not having update so.. save the data once)
			 userRepo.save(user);
		    
			 return true;
		}//if
		
		//password is not match so return false
		return false;
		
	}//method

	@Override
	public boolean forgetPwd(String emailId) {
		
 /*   UserRegEntity userEntity=new UserRegEntity();
		//set the mail
		userEntity.setEmail(emailId);
		
		//set the mail in example
		Example<UserRegEntity> example = Example.of(userEntity);
		
		//find the mail help of example
		Optional<UserRegEntity> findOne = userRepo.findOne(example);
*/		//duplicate code
		
		Optional<UserRegEntity> findOne = getUserByEmail(emailId);
		
		if(findOne.isPresent())
		{
			UserRegEntity user = findOne.get();//based on the email all user data is get it
		
			String email = user.getEmail();//user mail id
			String password = user.getPassword();//user password
			
			//TODO : send the mail (email,password)
			
			return true;
		}
		else {
			//email is not there
			return false;	
		}
		
	}//method

	private Optional<UserRegEntity> getUserByEmail(String emailId) {
		UserRegEntity userEntity=new UserRegEntity();
		userEntity.setEmail(emailId);
		
		Example<UserRegEntity> example = Example.of(userEntity);
		Optional<UserRegEntity> findOne = userRepo.findOne(example);
		return findOne;
	}

	private String readUnlockAccEmailBody(UserRegEntity userEntity) 
	{
	
		StringBuffer sb=new StringBuffer(AppConstants.EMPTY_STR);
		String mailBody=AppConstants.EMPTY_STR;
		
try {
		
		String fileName = appProp.getMessages().get(AppConstants.FILE_EMAILBODY);
		
		FileReader file = new FileReader(fileName);
		
		BufferedReader br=new BufferedReader(file);
		
		String line = br.readLine();//read fast line
		
		while(line != null)//read all line so
		{
			sb.append(line);//read the line and store in StringBuffer
			line=br.readLine();//read the next line onwards
			
		}//while
		
		br.close();
		
		//decryption password
		String decryptPwd = PWDUtils.decryptMsg(userEntity.getPassword());
		
		System.out.println("Mail sending password is :: "+decryptPwd);
		//some dynamically value change the file
		//but StringBuffer is not work properly so convert to String and change them
		 mailBody=sb.toString();
		
		mailBody=mailBody.replaceAll(AppConstants.FNAME,userEntity.getFname());
		mailBody=mailBody.replaceAll(AppConstants.LNAME,userEntity.getLname());
		mailBody=mailBody.replaceAll(AppConstants.TEMP_PWD,userEntity.getPassword());
		mailBody=mailBody.replaceAll(AppConstants.EMAIL,decryptPwd);
		
		}
		catch (FileNotFoundException fe) {
			fe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return mailBody;
		
	}//method
	
}//class
