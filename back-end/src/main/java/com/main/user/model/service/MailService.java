package com.main.user.model.service;

import com.main.user.model.dto.MailMessage;

public interface MailService {
    public String sendMail(MailMessage mailMessage, String type);
    public String createCode();
    public String setContext(String code, String type);
    public boolean checkCode(String code);
}
