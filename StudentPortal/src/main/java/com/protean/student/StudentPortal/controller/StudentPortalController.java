package com.protean.student.StudentPortal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		return "login.jsp";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		return "login.jsp";
	}
	
	/*
	 * @RequestMapping("/register") public String register(){
	 * System.out.println("register"); return "register.html"; }
	 */
	
	@RequestMapping("/registerUser")
	public String registerUser(RegisterUserDetails registerDetails){
		String password = new BCryptPasswordEncoder().encode(registerDetails.getPassword());
		registerDetails.setPassword(password);
		userDetails.setUsername(registerDetails.getUserName());
		userDetails.setPassword(password);
		userDetails.setUser_role("USER");
		studentService.registerUser(registerDetails, userDetails);
		return "login.jsp";
	}
	
	@RequestMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login.jsp";
    }
	
}
