package com.example.demo.service;

import com.example.demo.global.Result;

public interface MailService {
    void checkMail(String receiveEmail, String subject, String emailMsg);

    Result sendTextMail(String receiveEmail, String subject, String emailMsg);
}
