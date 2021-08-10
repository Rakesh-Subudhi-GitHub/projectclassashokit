package com.rk.password;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Encoder;

public class Encryption {
	
	public static void main(String[] args) throws Exception {
	
		String password="ABC";
		
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");//SHA-1 Algo use
		
		messageDigest.reset();//messageDigest having every thing to remove 
		
		messageDigest.update(password.getBytes());//store the password
		
		byte[] digest = messageDigest.digest();//execute the encryption
		
		String digestPwd=new String(digest);//converting byte to String
		
		System.out.println("Encryption password is  "+digestPwd);
		
		
		
		//encoder
		Encoder encoder = Base64.getEncoder();
		
		String encodeToString = encoder.encodeToString(digest);
		
		System.out.println("Encry+Encoder botha attached :: "+encodeToString);
		
	}//main
}//class

