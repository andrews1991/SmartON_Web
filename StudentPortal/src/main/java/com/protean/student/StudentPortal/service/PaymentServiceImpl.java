package com.protean.student.StudentPortal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.protean.student.StudentPortal.model.TransactionDetails;
import com.protean.student.StudentPortal.repository.PaymentDao;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	PaymentDao paymentDao;
	
	@Override
	public void savePaymentData(TransactionDetails transDetails) {
		paymentDao.save(transDetails);
	}

	@Override
	public TransactionDetails getByMailId(String email) {
		return paymentDao.findByUserMail(email);
	}

}
