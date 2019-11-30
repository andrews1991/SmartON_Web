package com.protean.student.StudentPortal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.protean.student.StudentPortal.model.EventDetails;
import com.protean.student.StudentPortal.repository.EventsDetailsRepository;

@Service
public class EventDetailsServiceImpl implements EventDetailsService {

	@Autowired
	private EventsDetailsRepository eventsDetailsRepository;
	
	
	@Override
	public String addEvent(List<EventDetails> eventDetails) {
		 eventsDetailsRepository.saveAll(eventDetails);
		 return "Saved Successfully";
	}

	public EventDetails addEventDetail(EventDetails eventdetails) {
		return eventsDetailsRepository.save(eventdetails);
	}
	
	@Override
	public EventDetails getEventById(Long eventId) {
		return eventsDetailsRepository.findByEventid(eventId);
	}

	@Override
	public List<EventDetails> getAllEvents() {
		return (List<EventDetails>) eventsDetailsRepository.findAll();
	}

	public EventDetails updateEventDetails(EventDetails eventDetails) {
		/*EventDetails eventDetailsFromDB = eventsDetailsRepository.findByEventid(eventDetails.getIdEventid());
		studentFromDB.setContact(student.);*/
		
		return eventsDetailsRepository.save(eventDetails);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	public int deleteEventDetail(Long eventid) {
		 eventsDetailsRepository.deleteEventDetail(eventid);
		 return 1;
		//return "Event Removel has been done";
		
	}

	@Override
	public List<EventDetails> findAllByDeletedflag(Long flag) {
		// TODO Auto-generated method stub
		return (List<EventDetails>) eventsDetailsRepository.findAllByDeletedflag(flag);
	}

	
	  public List<EventDetails> getOngoingEventsByCatogery(String catogery,String
	  type){ return (List<EventDetails>)
	  eventsDetailsRepository.findAllByEventCategoryandType(catogery,type); }
	 
	
	
	/*
	 * @Override public List<EventDetails> findAllByDeletedflag(long flag) { // TODO
	 * Auto-generated method stub return null; }
	 */

}
