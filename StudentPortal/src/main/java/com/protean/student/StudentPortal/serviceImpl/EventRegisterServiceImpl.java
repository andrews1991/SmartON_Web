package com.protean.student.StudentPortal.serviceImpl;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.protean.student.StudentPortal.model.EventDetails;
import com.protean.student.StudentPortal.model.EventRegister;
import com.protean.student.StudentPortal.model.RegisterUserDetails;
import com.protean.student.StudentPortal.repository.EventRegisterRepo;
import com.protean.student.StudentPortal.service.EventRegisterService;

@Service
public class EventRegisterServiceImpl implements EventRegisterService{
	
	@Autowired
	private EventRegisterRepo eventRegister;
	
	public EventRegister addEventRegistrationDetail(EventRegister eventregister) {
		Long uaerId=(long) 1;
		/*
		 * int local=eventRegister.checknoAllowedEvt(uaerId);
		 * System.out.println("local................."+local);
		 */
		/*
		 * for(RegisterUserDetails item : local){
		 * System.out.println(item.getNoofevtallowed()); }
		 */
		
		
		/*
		 * ListIterator<RegisterUserDetails> listItr = local.listIterator();
		 * 
		 * while(listItr.hasNext()) { RegisterUserDetails reg=listItr.next();
		 * 
		 * System.out.println("**********************"+reg.getNoofevtallowed()); }
		 */
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
