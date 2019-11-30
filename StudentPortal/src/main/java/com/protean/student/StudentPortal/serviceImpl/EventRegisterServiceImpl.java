package com.protean.student.StudentPortal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.protean.student.StudentPortal.model.EventDetails;
import com.protean.student.StudentPortal.model.EventRegister;
import com.protean.student.StudentPortal.repository.EventRegisterRepo;
import com.protean.student.StudentPortal.service.EventRegisterService;

@Service
public class EventRegisterServiceImpl implements EventRegisterService{
	
	@Autowired
	private EventRegisterRepo eventRegister;
	
	public EventRegister addEventRegistrationDetail(EventRegister eventregister) {
		return eventRegister.save(eventregister);
	}

	@Override
	public List<EventRegister> getEventRegisterDetailsByEventId(Long id) {
		System.out.println("========="+id);
		return eventRegister.getEventRegisterDetailsByEventId(id);
		
	}
	@Override
	public List<EventRegister> UpdateEventRegisterAttendence(List<EventRegister> evtregDetail) {
		return eventRegister.saveAll(evtregDetail);
		// return "Saved successfully";
		
	}

	
	
	
	
}
