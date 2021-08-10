package com.rk.password;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class PasswordEncryption 
{
	
	//encoded
	public static String encodeMsg(String text)
	{
		Encoder encoder = Base64.getEncoder();
		String encodeToString = encoder.encodeToString(text.getBytes());
		
		return encodeToString;
	}
	
	//decoded
	public static String decodedMsg(String encodeStr)
	{
		Decoder decoder = Base64.getDecoder();
		byte[] decode = decoder.decode(encodeStr);
		
		return new String(decode);
	}
	
    public static void main( String[] args )
    {
        String encodeMsg = encodeMsg("ABC");
        
        System.out.println("Encoded message is : "+encodeMsg);
        
        String decodedMsg = decodedMsg(encodeMsg);
        System.out.println("Decoded message is : "+decodedMsg);
        
    }//main
}//class
