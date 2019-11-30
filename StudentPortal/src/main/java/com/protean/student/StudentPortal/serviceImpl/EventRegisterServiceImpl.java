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

	@Override
	public List<EventRegister> getEventRegisterDetailsByEventId(Long id) {
		return eventRegister.findAllByEventid(id);
		
	}
	@Override
	public String UpdateEventRegisterAttendence(List<EventRegister> evtregDetail) {
		 eventRegister.saveAll(evtregDetail);
		 return "Saved successfully";
		
	}

	
	
	
	
}
