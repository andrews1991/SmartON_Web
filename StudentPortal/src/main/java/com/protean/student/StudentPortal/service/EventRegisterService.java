package com.protean.student.StudentPortal.service;

import java.util.List;

import org.springframework.stereotype.Component;


import com.protean.student.StudentPortal.model.EventRegister;

@Component
public interface EventRegisterService {
	public List<EventRegister> getEventRegisterDetailsByEventId(Long id);

	String UpdateEventRegisterAttendence(List<EventRegister> evtregDetails); 

}
