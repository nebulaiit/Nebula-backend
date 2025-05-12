package com.nebula.Nebula.tutorial.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.tutorial.dto.ContactFormRequest;
import com.nebula.Nebula.tutorial.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContactController {

    @Autowired
    private MailService mailService;

    @PostMapping("/contact")
    public ResponseEntity<ResponseBodyDto> handleContactForm(@RequestBody ContactFormRequest request) {
        ResponseBodyDto responseBodyDto = mailService.sendContactMail(request);
        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);
    }
}
