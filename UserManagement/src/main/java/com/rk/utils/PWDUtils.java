package com.rk.utils;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PWDUtils {

	private static final String SECRET_KEY="abc123xyz123abcd";
	private static final String INIT_VECTOR="abc123xyz123abcd";


	//Encryption
	public static String encryptMsg(String msg)  {
	
try {
		IvParameterSpec ivParamSpec=new IvParameterSpec(INIT_VECTOR.getBytes());
		
		SecretKeySpec secreteKeySpec=new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"),"AES");
		
		Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5PADDING");
		
		cipher.init(Cipher.ENCRYPT_MODE, secreteKeySpec,ivParamSpec);
		
		byte[] encrypted = cipher.doFinal(msg.getBytes());
		
		return Base64.getEncoder().encodeToString(encrypted);
		
		}//try
		
		catch(InvalidAlgorithmParameterException iap) {
			iap.printStackTrace();
		}
		catch (GeneralSecurityException ge) {
			ge.printStackTrace();
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	//Decryption
	public static String decryptMsg(String msg)  {

	try {
		
			IvParameterSpec ivParamSpec=new IvParameterSpec(INIT_VECTOR.getBytes());
			
			SecretKeySpec secreteKeySpec=new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"),"AES");
			
			Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5PADDING");
			
			cipher.init(Cipher.DECRYPT_MODE, secreteKeySpec,ivParamSpec);
			
			byte[] decodeMsg = Base64.getDecoder().decode(msg);
			
			byte[] decrypted = cipher.doFinal(decodeMsg);
			
			return new String(decrypted);
			
			}//try
			
			catch(InvalidAlgorithmParameterException iap) {
				iap.printStackTrace();
			}
			catch (GeneralSecurityException ge) {
				ge.printStackTrace();
			}
			catch( Exception e) {
				e.printStackTrace();
			}
		
		return null;
	}
	
}//class
