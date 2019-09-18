package com.protean.student.StudentPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.protean.student.StudentPortal.service.StudentDetailsService;

@Controller
public class StudentPortalController {

	@Autowired
	StudentDetailsService studentService;
	
	@RequestMapping("/")
	public String home(){
		System.out.println(studentService.fetchStudentDetails());
		/*
		 * ModelAndView mv = new ModelAndView("home.html"); mv.addObject("name",
		 * studentService.fetchStudentDetails()); return mv;
		 */
		return "home.html";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "index.html";
	}
	
}
