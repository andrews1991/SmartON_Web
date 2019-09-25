package com.protean.student.StudentPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.protean.student.StudentPortal.model.RegisterUserDetails;
import com.protean.student.StudentPortal.model.StudentUserDetails;
import com.protean.student.StudentPortal.service.StudentUserDetailsService;

@Controller
public class StudentPortalController {
	
	@Autowired
	StudentUserDetails userDetails;

	@Autowired
	StudentUserDetailsService studentService;
	
	@RequestMapping("/")
	public String home(){
		return "home.html";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "index.html";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		return "index.html";
	}
	
	@RequestMapping("/register")
	public String register(){
		System.out.println("register");
		return "register.html";
	}
	
	@RequestMapping("/registerUser")
	public String registerUser(RegisterUserDetails registerDetails){
		String password = new BCryptPasswordEncoder().encode(registerDetails.getPassword());
		registerDetails.setPassword(password);
		userDetails.setUsername(registerDetails.getUserName());
		userDetails.setPassword(password);
		userDetails.setUser_role("USER");
		studentService.registerUser(registerDetails, userDetails);
		return "index.html";
	}
	
}
