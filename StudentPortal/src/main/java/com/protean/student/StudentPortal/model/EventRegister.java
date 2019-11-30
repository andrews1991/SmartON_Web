package com.protean.student.StudentPortal.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="event_Register")
public class EventRegister {
	
	@Id
	@GeneratedValue
	@Column(name = "eventregisterid",unique = true)
	private Long eventRegisterId;
	
	@Column(name="fk_eventid")
	private Long eventid;
	
	@Column(name="fk_userid")
	private Long userid;
	
	@Column(name="ispresent")
	private Boolean ispresent;
	
	@Column(name="iscancelled")
	private Boolean iscancelled;
	
	@Column(name="updatedon")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updatedon;
	
	@Column(name="updatedby")
	private long updatedby;
	
	@Column(name="createdby")
	private long createdby;
	
	@Column(name="createdon")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdon;
	
	@Column(name="deletedflag")
	private Long deletedflag;

	public Long getEventRegisterId() {
		return eventRegisterId;
	}

	public void setEventRegisterId(Long eventRegisterId) {
		this.eventRegisterId = eventRegisterId;
	}

	public Long getEventid() {
		return eventid;
	}

	public void setEventid(Long eventid) {
		this.eventid = eventid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Boolean getIspresent() {
		return ispresent;
	}

	public void setIspresent(Boolean ispresent) {
		this.ispresent = ispresent;
	}

	public Boolean getIscancelled() {
		return iscancelled;
	}

	public void setIscancelled(Boolean iscancelled) {
		this.iscancelled = iscancelled;
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

	public Long getDeletedflag() {
		return deletedflag;
	}

	public void setDeletedflag(Long deletedflag) {
		this.deletedflag = deletedflag;
	}

	

}
