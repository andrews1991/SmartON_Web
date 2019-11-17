package com.protean.student.StudentPortal.service;

import org.springframework.stereotype.Service;

import com.protean.student.StudentPortal.model.TransactionDetails;

@Service
public interface PaymentService {
	
	public void savePaymentData(TransactionDetails transDetails);
	
	public TransactionDetails getByMailId(String email);

}
