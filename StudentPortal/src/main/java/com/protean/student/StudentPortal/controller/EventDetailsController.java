package com.protean.student.StudentPortal.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@Autowired(required=true)
	EventDetailsService eventDetailsService;
	
	@Autowired(required = true)
	EventRegisterService eventRegisterDetailsService;
	
	@Autowired
	RegistrationDao registrationDao;
	
	@Autowired
	MailSenderService mailSenderService;
	
	 @PostMapping(value="/addEvent")
		//@PostMapping(value="/add")
		public String addEvent(@RequestBody List<EventDetails> eventdetails) {
			 
			 System.out.println("Successfully Entered..............");
			return eventDetailsService.addEvent(eventdetails);
		}
	
	
	  @PostMapping(value="/addEventDetail")
	  //@PostMapping(value="/add") 
	  public EventDetails addEventDetail(@RequestBody EventDetails  eventdetails) throws ParseException {
		  String fdate=null;
		  System.out.println("***********************************"+eventdetails.getEventDate());
		 // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		/*
		 * System.out.println("***********************************");
		 * if(eventdetails.getEventDate()!=null) {
		 * fdate=eventdetails.getEventDate().toString(); SimpleDateFormat sdf = new
		 * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); SimpleDateFormat output = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Date d = (Date) sdf.parse(fdate);
		 * String formattedTime = output.format(d);
		 * System.out.println("formattedTime::::::::::::::::::::::::::::"+formattedTime)
		 * ;
		 * 
		 * }
		 */
		  return eventDetailsService.addEventDetail(eventdetails);
		  }
	 
	@GetMapping(value="/getEventDetail/{id}")
	public EventDetails getEventById(@PathVariable Long id) {
		return eventDetailsService.getEventById(id);
	}
	
	@GetMapping(value="getOngoingEvents")
	public List<EventDetails> getAllNonDeletedEvents(){
		System.out.println("Succes..................");
		long flag=0;
		return eventDetailsService.findAllByDeletedflag(flag);
	}
	@GetMapping(value="getOngoingEventsByCatogery/{catogery}/{type}")
	public List<EventDetails> getOngoingEventsByCatogery( @PathVariable String catogery,@PathVariable String type){
		System.out.println("Succes..................");
		//long flag=0;
		return eventDetailsService.getOngoingEventsByCatogery(catogery,type);
	}
	@GetMapping(value="/getallEventDetails") 
	public List<EventDetails> getAllStudents() {
		return eventDetailsService.getAllEvents();
	}
	
	
	
	@GetMapping(value="/getRegisteredEventDetailByEventid/{id}")
	public List<EventRegister> getEventRegisterDetailsByEventType(@PathVariable Long id) {
		return eventRegisterDetailsService.getEventRegisterDetailsByEventId(id);
	}
	
	 @PostMapping(value="/UpdateEventRegisterAttendence")
		//@PostMapping(value="/add")
		public String UpdateEventRegisterAttendence(@RequestBody List<EventRegister> evtregDetails) {
			 
			 System.out.println("Successfully Entered..............");
			return eventRegisterDetailsService.UpdateEventRegisterAttendence(evtregDetails);
		}
	
	
	
	
	@PostMapping(value="/updateEventDetail/{id}")
	public EventDetails updateStudent(@RequestBody EventDetails eventDetails) {
		System.out.println("Success");
		return eventDetailsService.updateEventDetails(eventDetails);
	}

	
	@PostMapping(value="/deleteEventDetail/{id}")
	public int deleteEventDetail(@RequestBody EventDetails eventDetails) {
		int message=0;
		if(eventDetails.getEventid()!=null) {
		long eventid=eventDetails.getEventid();
				message= eventDetailsService.deleteEventDetail(eventid);
		}else {
			message=0;			
		}
		return message;
	}
	
	@GetMapping(value="/getUser/{userName}")
	public RegisterUserDetails getStudentById(@PathVariable String userName) {
		return registrationDao.findByUserName(userName);
	}
	
	@GetMapping(value="/sendMail/{mailID}")
	public String send(@PathVariable String mailID) throws MessagingException {
		try {
			RegisterUserDetails registerUserDetails = registrationDao.findByEmail(mailID);
			mailSenderService.sendEmail(registerUserDetails);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}
	
}
