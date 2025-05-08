package com.nebula.Nebula.service;


import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.dto.ContactFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public ResponseBodyDto sendContactMail(ContactFormRequest request) {

        if (request != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("ecom.platform12@gmail.com");
            message.setSubject("New Contact Form Submission");
            message.setText(
                    "Name: " + request.getFullName() + "\n" +
                            "Email: " + request.getEmail() + "\n" +
                            "Phone: +" + request.getPhoneNumber() + "\n" +
                            "Message: " + request.getMessage()
            );

            javaMailSender.send(message);

            return ResponseBodyDto.builder().code(200).message("Mail sent successfully").build();
        }

        return ResponseBodyDto.builder().code(400).message("Error while sending mail").build();

    }

}
