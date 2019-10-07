package com.protean.student.StudentPortal.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Event")
public class EventDetails {

	@Id
	@GeneratedValue
	//@Column(name = "eventid")
	private Long eventid;
	
	public Long getEventid() {
		return eventid;
	}
	public void setEventid(Long eventid) {
		this.eventid = eventid;
	}
	//@Column(name = "EventType")
	private String eventType;
	
	//@Column(name = "EventCatogery")
	private String eventCatogery;
	
	//@Column(name = "EventName")
	private String eventName;
	
	//@Column(name = "EventOrgName")
	private String eventOrgName;
	
	//@Column(name = "EventDate")
	private Date eventDate;
	
	//@Column(name = "OnGoingEvent")
	private String onGoingEvent;
	
	//@Column(name="image")
	private byte[] evenyImage;
	
	private Long deletedflag;
	

	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventCatogery() {
		return eventCatogery;
	}
	public void setEventCatogery(String eventCatogery) {
		this.eventCatogery = eventCatogery;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventOrgName() {
		return eventOrgName;
	}
	public void setEventOrgName(String eventOrgName) {
		this.eventOrgName = eventOrgName;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getOnGoingEvent() {
		return onGoingEvent;
	}
	public void setOnGoingEvent(String onGoingEvent) {
		this.onGoingEvent = onGoingEvent;
	}
	
	
	public EventDetails() {
		super();
	}
	public EventDetails(Long id, byte[] image){
		super();
		this.eventid=id;
		this.setEvenyImage(image);
		
	}
	public byte[] getEvenyImage() {
		return evenyImage;
	}
	public void setEvenyImage(byte[] evenyImage) {
		this.evenyImage = evenyImage;
	}
	public Long getDeletedflag() {
		return deletedflag;
	}
	public void setDeletedflag(Long deletedflag) {
		this.deletedflag = deletedflag;
	}
	
	
	

}
