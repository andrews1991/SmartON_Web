package com.protean.student.StudentPortal.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class commonutils implements Serializable {
	@Column(name="updatedon")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "IST")
	@Temporal(value=TemporalType.TIMESTAMP)
	public Date updatedon;
	
	@Column(name="updatedby")
	public long updatedby;
	
	@Column(name="createdby")
	public long createdby;
	
	@Column(name="createdon")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "IST")
	@Temporal(value=TemporalType.TIMESTAMP)
	public Date createdon;
	
	@Column(name="deletedflag")
	public Long deletedflag;

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
