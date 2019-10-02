package com.protean.student.StudentPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.protean.student.StudentPortal.model.RegisterUserDetails;

@Repository
public interface RegistrationDao extends JpaRepository<RegisterUserDetails, Integer> {

}
