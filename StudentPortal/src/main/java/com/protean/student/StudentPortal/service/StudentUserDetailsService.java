package com.protean.student.StudentPortal.service;

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
		System.out.println(userDetails.getUsername()+" "+userDetails.getPassword());
		return new UserDetailsImpl(userDetails);
	}
	
	public void registerUser(RegisterUserDetails registerDetails,StudentUserDetails userDetails) {
		studentDao.save(userDetails);
		registerDao.save(registerDetails);
	}

}
