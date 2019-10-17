package com.protean.student.StudentPortal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.protean.student.StudentPortal.model.RegisterUserDetails;
import com.protean.student.StudentPortal.model.StudentUserDetails;
import com.protean.student.StudentPortal.service.MailSenderService;
import com.protean.student.StudentPortal.service.StudentUserDetailsService;

@Controller
public class StudentPortalController {
	
	@Autowired
	StudentUserDetails userDetails;

	@Autowired
	StudentUserDetailsService studentService;
	
	@Autowired
	MailSenderService mailSender;
	
	
	@RequestMapping("/")
	public String home(Authentication authentication,Model model){
		String userName = authentication.getName();
		RegisterUserDetails regDetails = studentService.getLogonDetails(userName);
		model.addAttribute("studentDetails", regDetails);
		model.addAttribute("userName", userName);
		return "dashboard.jsp";
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
	@ResponseBody
	public String registerUser(RegisterUserDetails registerDetails){
		String password = new BCryptPasswordEncoder().encode(registerDetails.getPassword());
		registerDetails.setPassword(password);
		userDetails.setUsername(registerDetails.getUserName());
		userDetails.setPassword(password);
		userDetails.setUser_role("USER");
		studentService.registerUser(registerDetails, userDetails);
		return "success";
	}
	
	@RequestMapping("/checkValidData")
	@ResponseBody
	public String checkValidData(String userName,String email) {
		JSONObject jsObj = studentService.registerValidityChecker(userName, email);
		return jsObj.toString();
	}
	
	@RequestMapping("/forgotPassword")
	@ResponseBody
	public String forgotPassword(@RequestParam(name="forgotEmail") String email) {
		System.out.println(email);
		RegisterUserDetails regObj = studentService.forgotPassword(email);
		String isValid = "valid";
		if(regObj != null) {
			//send mail
			mailSender.sendEmail(email);
		}else {
			isValid = "invalid";
		}
		return isValid;
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
