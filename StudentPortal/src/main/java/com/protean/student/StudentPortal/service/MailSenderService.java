package com.protean.student.StudentPortal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.protean.student.StudentPortal.model.RegisterUserDetails;

@Service
public class MailSenderService {

	@Autowired
	private JavaMailSender jms; 
	
	@Autowired
	public MailSenderService(JavaMailSender javaMailSender) {
		this.jms = javaMailSender;
	}
	
	public void sendEmail(RegisterUserDetails registerUserDetails) {
		//int rand_int1 = new Random().nextInt(10000);
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(registerUserDetails.getEmail());

        msg.setSubject("Thank You for Register in Tag #");
        String body = "Welcome to” Tag “The Professional Academy \r\n" + 
        		"TAG a unique concept in bridging your career in line to current corporate requirement.\r\n" + 
        		"Freedom to design our own life is the greatest gift we can ever get and TAG academy is the right platform to help you get closer to it.\r\n" + 
        		"We are glad you choose TAG !!\r\n" + 
        		"Welcome to the Academy !!";
        body+="\n \n Use this Referal code "+registerUserDetails.getProfileID()+" and You have Earn Rewards! "+registerUserDetails.getRewpoints()*1000+" Happy Learning!";
        msg.setText(body);
        jms.send(msg);
	}
}
