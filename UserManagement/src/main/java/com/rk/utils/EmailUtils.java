package com.rk.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSend;
	
	
	public boolean sendEmail(String tomail,String sub,String body)
	{
		Boolean isSent=false;
		
		 MimeMessage mimeMessage = mailSend.createMimeMessage();
		
		 try {
			
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            
            mimeMessageHelper.setSubject(sub);
            mimeMessageHelper.setTo(tomail);
            //mimeMessageHelper.setText(body);
            mimeMessageHelper.setText(body,true);//html is true (html all text convertions)
 
            mailSend.send(mimeMessageHelper.getMimeMessage());
            isSent=true;
		 }//try
		
		catch (Exception e) {	
			e.printStackTrace();
		}//catch
		
		return isSent;
	}//method
}//class
