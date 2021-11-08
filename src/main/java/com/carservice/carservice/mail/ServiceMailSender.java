package com.carservice.carservice.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ServiceMailSender {

    private JavaMailSender javaMailSender;

    public ServiceMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("carservice0044@gmail.com");
        message.setTo(to);
        message.setSubject("Car Service: DONE");
        message.setText("Hello, just to inform you that your vehicle service is finished.");
        javaMailSender.send(message);
    }
}
