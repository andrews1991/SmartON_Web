package com.protean.student.StudentPortal.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.protean.student.StudentPortal.model.RegisterUserDetails;
import com.protean.student.StudentPortal.repository.RegistrationDao;
import com.protean.student.StudentPortal.repository.StudentDao;

@Service
public class StudentUserDetailsService implements UserDetailsService {
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private RegistrationDao registerDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RegisterUserDetails userDetails = studentDao.findByUserName(username);
		if(userDetails == null)
			throw new UsernameNotFoundException("User name invalid");
		return new UserDetailsImpl(userDetails);
	}
	
	public void registerUser(RegisterUserDetails registerDetails) {
		registerDetails.setProfileID(GenarateProfileID(registerDetails));
		registerDao.save(registerDetails);
	}
	
	public void updateRewards(Long rewardPoints, String userName) {
		registerDao.updateRewards(rewardPoints,userName);
	}
	
	public JSONObject registerValidityChecker(String userName,String email) {
		RegisterUserDetails registerDetails = registerDao.findByUserName(userName);
		RegisterUserDetails registerDetails1 = registerDao.findByEmail(email);
		JSONObject jsObj = new JSONObject();
		if(registerDetails != null) {
			jsObj.put("userName", "invalid");
		}else {
			jsObj.put("userName", "valid");
		}
		if(registerDetails1 != null) {
			jsObj.put("email", "invalid");
		}else {
			jsObj.put("email", "valid");
		}
		return jsObj;
	}
	
	public RegisterUserDetails getLogonDetails(String userName) {
		RegisterUserDetails regDetails = registerDao.findByUserName(userName);
		return regDetails;
	}
	
	public RegisterUserDetails forgotPassword(String email) {
		RegisterUserDetails registerDetails = registerDao.findByEmail(email);
		return registerDetails;
	}

	public String GenarateProfileID(RegisterUserDetails registerDetails) {
		String profileID="";
		Date dob = registerDetails.getUserDob();
		String name = registerDetails.getFirstName().substring(0,1).toUpperCase()+registerDetails.getLastName().substring(0,1).toUpperCase();
	    System.out.println(dob);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dobdate = formatter.format(dob);
	    profileID= registerDetails.getCity().substring(0,3).toUpperCase()+dobdate.substring(8,10)+ dobdate.substring(5,7)+dobdate.substring(2,4)+ name;

		return profileID;
		
	}
	
	public void updateUserDetails(RegisterUserDetails registerDetails) {
		registerDao.save(registerDetails);
	}
	
	public RegisterUserDetails getUserDetailsByMail(String email) {
		RegisterUserDetails registerDetails = registerDao.findByEmail(email);
		return registerDetails;
	}
	
	public RegisterUserDetails getUserDetailsByProfileId(String profileId) {
		return registerDao.findByProfileID(profileId);
	}
}
