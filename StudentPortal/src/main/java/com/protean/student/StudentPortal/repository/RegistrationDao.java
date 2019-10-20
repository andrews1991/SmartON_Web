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
    @Query(value = "UPDATE user_details set reward_points=(select count(1) from user_details where referal_code=:profileID ) where profileID=:profileID",nativeQuery = true)
   
	void updateRewards(@Param("profileID") String profileID);

	
	public RegisterUserDetails findByUserName(String userName);
	
	public RegisterUserDetails findByEmail(String email);
	
	

}
