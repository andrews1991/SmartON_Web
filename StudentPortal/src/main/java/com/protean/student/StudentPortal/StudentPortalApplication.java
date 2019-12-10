package com.protean.student.StudentPortal;

import org.apache.tomcat.jni.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.protean.student.StudentPortal.controller.EventDetailsController;

@SpringBootApplication      
//@ComponentScan({"com.protean.student.StudentPortal.controller","controller"})
public class StudentPortalApplication {

	public static void main(String[] args) {
		//new File(EventDetailsController.uploadDirectory).mkdir);
		SpringApplication.run(StudentPortalApplication.class, args);
	}

}
