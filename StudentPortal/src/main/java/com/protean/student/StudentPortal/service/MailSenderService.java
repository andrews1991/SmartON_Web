package com.protean.student.StudentPortal.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

	@Autowired
	private JavaMailSender jms; 
	
	public void sendEmail(String email) {
		//int rand_int1 = new Random().nextInt(10000);
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email ");

        jms.send(msg);
	}
}
