package com.protean.student.StudentPortal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.protean.student.StudentPortal.model.EventDetails;
import com.protean.student.StudentPortal.repository.EventsDetailsRepository;

@Service
public class EventDetailsServiceImpl implements EventDetailsService {

	@Autowired
	private EventsDetailsRepository eventsDetailsRepository;
	
	
	@Override
	public EventDetails addEvent(EventDetails eventDetails) {
		return eventsDetailsRepository.save(eventDetails);
	}

	@Override
	public EventDetails getEventById(Integer eventId) {
		return eventsDetailsRepository.findByEventid(eventId);
	}

	@Override
	public List<EventDetails> getAllEvents() {
		return (List<EventDetails>) eventsDetailsRepository.findAll();
	}

	@Override
	public EventDetails updateEventDetails(EventDetails eventDetails) {
		/*EventDetails eventDetailsFromDB = eventsDetailsRepository.findByEventid(eventDetails.getIdEventid());
		studentFromDB.setContact(student.);*/
		
		return eventsDetailsRepository.save(eventDetails);
	}

	@Override
	public void delete(Integer id) {
		//eventsDetailsRepository.delete(id);

	}

}
