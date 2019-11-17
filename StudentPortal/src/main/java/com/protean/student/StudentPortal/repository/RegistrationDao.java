package com.protean.student.StudentPortal.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.protean.student.StudentPortal.model.RegisterUserDetails;

@Repository
public interface RegistrationDao extends JpaRepository<RegisterUserDetails, Integer> {
	
	@Transactional
    @Modifying
    @Query(value = "UPDATE user_details SET rewpoints = :rewardPoints WHERE username = :userName",nativeQuery = true)
	void updateRewards(@Param("rewardPoints") Long rewardPoints, @Param("userName") String userName);

	
	public RegisterUserDetails findByUserName(String userName);
	
	public RegisterUserDetails findByEmail(String email);
	
	public RegisterUserDetails findByProfileID(String profileID);
	
	

}
