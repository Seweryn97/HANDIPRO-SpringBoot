package com.example.HANDIPRO.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class VerifyEmailSender {

    JavaMailSender javaMailSender;

    @Autowired
    public VerifyEmailSender(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage mailMessage){
        javaMailSender.send(mailMessage);
    }


}
