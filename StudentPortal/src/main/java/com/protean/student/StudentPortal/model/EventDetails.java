package com.protean.student.StudentPortal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="event_details")
public class EventDetails {
	
	@Id
	@GeneratedValue
	@Column(name = "eventid",unique = true)
	private Long eventid;
	
	@Column(name="event_duration")
	private String eventDuration;
	
	@Column(name="updatedon")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "IST")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date updatedon;
	
	@Column(name="updatedby")
	private long updatedby;
	
	@Column(name="createdby")
	private long createdby;
	
	@Column(name="createdon")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "IST")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date createdon;
	
	@Column(name="event_description")
	private String eventDescription;
	
	@Column(name="trainer_name")
	private String trainerName;
	
	@Column(name="totnum_seats")
	private Long totnumSeats;
	
	@Column(name="event_venue")
	private String eventVenue;
	
	@Column(name = "event_type")
	private String eventType;
	
	@Column(name = "event_catogery")
	private String eventCatogery;
	
	@Column(name = "event_name")
	private String eventName;
	
	@Column(name = "eventorganizer_name")
	private String eventOrgName;
	
	
	@Column(name = "event_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "IST")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date eventDate;
	
	@Column(name = "ongoing_event")
	private String onGoingEvent;
	
	@Column(name="event_image",columnDefinition = "blob")
	private byte[] eventImage;
	
	@Column(name="deletedflag")
	private Long deletedflag;

	public Long getEventid() {
		return eventid;
	}

	public void setEventid(Long eventid) {
		this.eventid = eventid;
	}

	public String getEventDuration() {
		return eventDuration;
	}

	public void setEventDuration(String eventDuration) {
		this.eventDuration = eventDuration;
	}

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}

	public long getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(long updatedby) {
		this.updatedby = updatedby;
	}

	public long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public String getEvent_description() {
		return eventDescription;
	}

	public void setEvent_description(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public Long getTotnumSeats() {
		return totnumSeats;
	}

	public void setTotnumSeats(Long totnumSeats) {
		this.totnumSeats = totnumSeats;
	}

	public String getEventVenue() {
		return eventVenue;
	}

	public void setEventVenue(String eventVenue) {
		this.eventVenue = eventVenue;
	}

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

	public byte[] getEventImage() {
		return eventImage;
	}

	public void setEvenyImage(byte[] eventImage) {
		this.eventImage = eventImage;
	}

	public Long getDeletedflag() {
		return deletedflag;
	}

	public void setDeletedflag(Long deletedflag) {
		this.deletedflag = deletedflag;
	}
	

	
	
	

}