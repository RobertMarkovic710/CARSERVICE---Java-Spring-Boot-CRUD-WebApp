package com.carservice.carservice.mailValidator;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
public class MailValidator {

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;

        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
