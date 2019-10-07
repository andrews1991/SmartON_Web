package com.protean.student.StudentPortal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.protean.student.StudentPortal.model.EventDetails;
import com.protean.student.StudentPortal.model.RegisterUserDetails;
import com.protean.student.StudentPortal.repository.RegistrationDao;
import com.protean.student.StudentPortal.service.EventDetailsService;


@RestController
//@RequestMapping("/api/events")
public class EventDetailsController {
	
	@Autowired(required=true)
	EventDetailsService eventDetailsService;
	
	@Autowired
	RegistrationDao registrationDao;
	
	 @PostMapping(value="/add",headers="Accept=application/json")
	//@PostMapping(value="/add")
	public EventDetails addEvent(@Valid @RequestBody EventDetails eventdetails) {
		return eventDetailsService.addEvent(eventdetails);
	}
	
	@GetMapping(value="/get/{id}")
	public EventDetails getStudentById(@PathVariable Long id) {
		return eventDetailsService.getEventById(id);
	}
	
	@GetMapping(value="/getall") 
	public List<EventDetails> getAllStudents() {
		return eventDetailsService.getAllEvents();
	}
	
	@PutMapping(value="/update/{id}")
	public EventDetails updateStudent(@RequestBody EventDetails eventDetails) {
		return eventDetailsService.updateEventDetails(eventDetails);
	}

	@GetMapping(value="/getUser/{userName}")
	public RegisterUserDetails getStudentById(@PathVariable String userName) {
		return registrationDao.findByUserName(userName);
	}
}
