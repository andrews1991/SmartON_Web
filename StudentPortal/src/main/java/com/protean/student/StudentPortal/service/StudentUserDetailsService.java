package com.protean.student.StudentPortal.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.protean.student.StudentPortal.model.RegisterUserDetails;
import com.protean.student.StudentPortal.model.StudentUserDetails;
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
		StudentUserDetails userDetails = studentDao.findByUsername(username);
		if(userDetails == null)
			throw new UsernameNotFoundException("User name invalid");
		return new UserDetailsImpl(userDetails);
	}
	
	public void registerUser(RegisterUserDetails registerDetails,StudentUserDetails userDetails) {
		studentDao.save(userDetails);
		registerDao.save(registerDetails);
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

}
