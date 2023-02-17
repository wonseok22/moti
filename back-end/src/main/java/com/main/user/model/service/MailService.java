package com.main.user.model.service;

import com.main.user.model.dto.MailMessage;

public interface MailService {
	String sendMail(MailMessage mailMessage, String type);
	
	String createCode();
	
	String setContext(String code, String type);
	
	boolean checkCode(String code);
	
	boolean checkAuthentication(String email);
}
