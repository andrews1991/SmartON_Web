package com.protean.student.StudentPortal.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.protean.student.StudentPortal.model.EventDetails;

@Repository
public interface EventsDetailsRepository extends JpaRepository<EventDetails, Long>{
	
	/*
	 * @Query(value="find EventDetails ed where eventid=:eventid ",nativeQuery=true)
	 * EventDetails findByEventid(Integer eventId);
	 */
	
	EventDetails findByEventid(Long eventId);
	
	/*
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query(value =
	 * "select * from event_Details where deletedflag= :flag",nativeQuery = true)
	 */
	@Transactional
    @Modifying
    @Query(value = "Select * from event_Details e where e.deletedflag = :flag and e.event_date>= CURRENT_TIMESTAMP",nativeQuery = true)	
	List<EventDetails> findAllByDeletedflag(@Param("flag") Long flag);
	
	@Transactional
    @Modifying
    @Query(value = "update event_Details set deletedflag=1 where eventid = :eventid",nativeQuery = true)
	int deleteEventDetail(@Param("eventid") Long eventid);
	
	
	@Transactional
    @Modifying
    @Query(value = "Select * from event_Details e where e.event_catogery =:catogery and e.event_type = :type",nativeQuery = true)
	List<EventDetails> findAllByEventCategoryandType(@Param("catogery") String catogery,@Param("type") String type);

	//List<EventDetails> findAllByDeletedflag(Long flag);
	
	
	

}
