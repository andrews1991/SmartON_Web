package com.protean.student.StudentPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.protean.student.StudentPortal.model.EventDetails;

@Repository
public interface EventsDetailsRepository extends JpaRepository<EventDetails, Long>{
	
	/*
	 * @Query(value="find EventDetails ed where eventid=:eventid ",nativeQuery=true)
	 * EventDetails findByEventid(Integer eventId);
	 */
	
	EventDetails findByEventid(Integer eventId);

}
