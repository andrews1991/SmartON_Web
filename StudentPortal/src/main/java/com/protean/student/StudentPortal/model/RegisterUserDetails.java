package com.protean.student.StudentPortal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="user_details")
public class RegisterUserDetails {
	private Long userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private Date userDob;
	private String gender;
	private String mobileNum;
	private String college;
	private String isPremium;
	private String city;
	private String state;
	private String profileID;
	private String referalCode;
	private Long rewardPoints;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid",unique = true)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name="firstname")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="lastname")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="username",unique = true)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="email",unique = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="dob")
	public Date getUserDob() {
		return userDob;
	}
	public void setUserDob(Date userDob) {
		this.userDob = userDob;
	}
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="mobile",unique = true)
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	@Column(name="college")
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	@Column(name="ispremium")
	public String getIsPremium() {
		return isPremium;
	}
	public void setIsPremium(String isPremium) {
		this.isPremium = isPremium;
	}
	@Column(name="city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="profileID",unique = true)
	public String getProfileID() {
		return profileID;
	}
	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}
	@Column(name="referalCode")
	public String getReferalCode() {
		return referalCode;
	}
	public void setReferalCode(String referalCode) {
		this.referalCode = referalCode;
	}
	@Column(name="rewardPoints",columnDefinition = "default 0") 
	public Long getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Long rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
}
