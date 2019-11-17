package com.protean.student.StudentPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.protean.student.StudentPortal.model.TransactionDetails;

@Repository
public interface PaymentDao extends JpaRepository<TransactionDetails, Integer>{

	public TransactionDetails findByUserMail(String userMail);
}
