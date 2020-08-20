package com.example.HANDIPRO.services;


import com.example.HANDIPRO.models.Physiotherapist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class VerifyEmailSender {

    JavaMailSender javaMailSender;

    @Autowired
    public VerifyEmailSender(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendMailNotification(Physiotherapist registration) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,"utf-8");

        mimeMessageHelper.setTo(registration.getEmail());
        mimeMessageHelper.setFrom("handipro1234@gamil.com");
        mimeMessageHelper.setSubject("Verify your email address");
        mimeMessageHelper.setText("<form action = http://localhost:8080/register/verifyemail/sewerynde@gmail.com method = 'post'><input type = 'submit' name = '_method' value = 'patch'></form>",true);

        javaMailSender.send(mimeMessage);
    }
}
