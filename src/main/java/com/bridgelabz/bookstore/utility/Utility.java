package com.bridgelabz.bookstore.utility;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class Utility {

    public static SimpleMailMessage verifyUserMail(String email, String token, String link) {
        System.out.println(email+" "+token+" "+ link);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);     //send mail

        msg.setSubject("testing"); //send message for user email account
        msg.setText("hello"+(link+token));  //send token for  user email  account
        return msg;

    }
}
