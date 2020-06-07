package com.bridgelabz.bookstore.utility;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SimpleMailUtility {

    public static SimpleMailMessage verifyUserMail(String email,  String link) {
        /*System.out.println(email+" "+ link);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);     //send mail
        msg.setSubject("Welcome to Book Store"); //send message for user email account
        msg.setText(link);  //send id for  user email  account
        return msg;*/
        return null;
    }
}
