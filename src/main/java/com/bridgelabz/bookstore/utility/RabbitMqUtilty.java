package com.bridgelabz.bookstore.utility;

import com.bridgelabz.bookstore.dto.RabbitMqDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqUtilty {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private JavaMailSender javaMailSender;

    //producer
    public void sendMessageToQueue(RabbitMqDto rabbitMqDto){
        final String exchange = "rabbitExchange";
        final String routingKey = "rabbitKey";
        System.out.println("hello");
        rabbitTemplate.convertAndSend(exchange, routingKey, rabbitMqDto);
    }

    public void send(RabbitMqDto email){
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
    public void receiveMessage(RabbitMqDto email) {
        send(email);
    }
}
