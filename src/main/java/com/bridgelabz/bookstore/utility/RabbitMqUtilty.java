package com.bridgelabz.bookstore.utility;

import com.bridgelabz.bookstore.dto.EmailDto;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.service.MessageReference;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqUtilty {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private JavaMailSender javaMailSender;

    //producer
    public void sendMessageToQueue(EmailDto rabbitMqDto){
        final String exchange = "rabbitExchange";
        final String routingKey = "rabbitKey";
        System.out.println("hello");
        rabbitTemplate.convertAndSend(exchange, routingKey, rabbitMqDto);
    }

    public void send(EmailDto email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setFrom(email.getFrom());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        javaMailSender.send(message);
        System.out.println("Mail Sent Succesfully");
    }

    //listener
    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void receiveMessage(EmailDto email) {
        send(email);
    }
    
    public static EmailDto getRabbitMq(String email, String token) {
    	
    	EmailDto rabbitMqDto = new EmailDto();
    	rabbitMqDto.setBody(MessageReference.REGISTRATION_MAIL_TEXT+token);
    	rabbitMqDto.setTo(email);
    	rabbitMqDto.setSubject("verification link");
		return rabbitMqDto;
    	
    }
    
    public static SimpleMailMessage verifyUserMail(String email, String token, String link){
    	
    	SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email); 
		msg.setSubject("test"); //send message for user email account
		msg.setText("hello"+(link+token));  //send token for  user email  account
		System.out.println("in simple mail :"+ email);
    	return msg;
    }
    
}
