package com.protean.student.StudentPortal.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.protean.student.StudentPortal.model.EventDetails;

@Component
public interface EventDetailsService {
	
	public EventDetails addEvent(EventDetails student);

	public EventDetails getEventById(Long id);

	public List<EventDetails> getAllEvents();

	public EventDetails updateEventDetails(EventDetails eventDetails);

	public void delete(Long id);


}
