package com.protean.student.StudentPortal.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.protean.student.StudentPortal.model.EventDetails;
import com.protean.student.StudentPortal.model.EventRegister;
import com.protean.student.StudentPortal.model.RegisterUserDetails;
import com.protean.student.StudentPortal.repository.RegistrationDao;
import com.protean.student.StudentPortal.service.EventDetailsService;
import com.protean.student.StudentPortal.service.EventRegisterService;
import com.protean.student.StudentPortal.service.MailSenderService;

@RestController
@RequestMapping("StudentPortal/Event")
public class EventDetailsController {

	 @Value("${spring.application.uploadDirectory}")
	   private String uploadDirectory;

	@Autowired(required = true)
	EventDetailsService eventDetailsService;

	@Autowired(required = true)
	EventRegisterService eventRegisterDetailsService;

	@Autowired
	RegistrationDao registrationDao;

	@Autowired
	MailSenderService mailSenderService;

	/* Add multiple events */
	@PostMapping(value = "/addEvent") /* Insert and update list of records */
	public String addEvent(@RequestBody List<EventDetails> eventdetails) {

		return eventDetailsService.addEvent(eventdetails);
	}

	/* Add single event at a time */
	@PostMapping(value = "/addEventDetail")
	public EventDetails addEventDetail(@RequestParam("date") String date,@RequestParam("image") MultipartFile image,@Valid EventDetails eventdetails) throws ParseException, IOException {
		byte[] data = image.getBytes();
		eventdetails.setEvenyImage(data);
		Date date1=(Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);  
		System.out.println(date1);
		eventdetails.setEventDate(date1);
		return eventDetailsService.addEventDetail(eventdetails);
	}

	@PostMapping(value = "/addEventDetailImage")
	public EventDetails addEventDetailImage(@RequestParam("date") String date,@RequestParam("image") MultipartFile photo, @Valid EventDetails eventdetails) throws ParseException, IOException {
			
		byte[] data = photo.getBytes();
		eventdetails.setEvenyImage(data);
		Date date1=(Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);  
		System.out.println(date1);
		eventdetails.setEventDate(date1);
		return eventDetailsService.addEventDetail(eventdetails);
		//return evt;
		

		 
	}

	/* List event details based on event id */
	@GetMapping(value = "/getEventDetail/{id}")
	public EventDetails getEventById(@PathVariable Long id) {
		return eventDetailsService.getEventById(id);
	}

	/* List out all ongoing events */
	@GetMapping(value = "getOngoingEvents")
	public List<EventDetails> getAllNonDeletedEvents() {
		long flag = 0;
		
		return eventDetailsService.findAllByDeletedflag(flag);
	}

	/* Listout event based on catagory and type of events */
	@GetMapping(value = "/getOngoingEventsByCatogery/{catogery}/{type}")
	public List<EventDetails> getOngoingEventsByCatogery(@PathVariable String catogery, @PathVariable String type) {
		System.out.println("Succes..................");
		// long flag=0;
		return eventDetailsService.getOngoingEventsByCatogery(catogery, type);
	}

	/* List out all the events without condition */
	@GetMapping(value = "/getallEventDetails")
	public List<EventDetails> getAllStudents() {
		return eventDetailsService.getAllEvents();
	}

	/* Update event details based on event id */
	@PostMapping(value = "/updateEventDetail/{id}")
	public EventDetails updateStudent(@RequestParam("date") String date,@RequestParam("image") MultipartFile image, @Validated EventDetails eventDetails) throws IOException, ParseException {
		
		byte[] data = image.getBytes();
		eventDetails.setEvenyImage(data);
		Date date1=(Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);  
		System.out.println(date1);
		eventDetails.setEventDate(date1);
		return eventDetailsService.updateEventDetails(eventDetails);
	}

	/* Remove event or delete Event */
	@GetMapping(value = "/deleteEventDetail/{id}")
	public int deleteEventDetail(@PathVariable Long id) {
		int message = 0;
		return eventDetailsService.deleteEventDetail(id);
	}

	@GetMapping(value = "/getUser/{userName}")
	public RegisterUserDetails getStudentById(@PathVariable String userName) {
		return registrationDao.findByUserName(userName);
	}

	@GetMapping(value = "/sendMail/{mailID}")
	public String send(@PathVariable String mailID) throws MessagingException {
		try {
			RegisterUserDetails registerUserDetails = registrationDao.findByEmail(mailID);
			mailSenderService.sendEmail(registerUserDetails);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}

	/***************************** EVENT REGISTRATION AND ATTENDENCE */

	/* Event Registration Sevice */
	@PostMapping(value = "/addEventRegistrationDetail")
	public EventRegister addEventRegistrationDetail(@RequestBody EventRegister eventregister) throws ParseException {

		return eventRegisterDetailsService.addEventRegistrationDetail(eventregister);
	}

	/* Get Event Registration Details based on eventid */
	@GetMapping(value = "/getRegisteredEventDetailByEventid/{id}")
	public List<EventRegister> getEventRegisterDetailsByEventType(@PathVariable Long id) {
		System.out.println("***************" + id);
		return eventRegisterDetailsService.getEventRegisterDetailsByEventId(id);
	}

	/* Student Attendence sevice */
	@PostMapping(value = "/UpdateEventRegisterAttendence")
	public List<EventRegister> UpdateEventRegisterAttendence(@RequestBody List<EventRegister> evtregDetails) {

		System.out.println("Successfully Entered..............");
		return eventRegisterDetailsService.UpdateEventRegisterAttendence(evtregDetails);
	}

}

