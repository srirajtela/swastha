package com.jsp.swasthik.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.swasthik.dto.Payment;

import com.jsp.swasthik.repo.PaymentRepo;
@Repository
public class PaymentDao {

	@Autowired
	private PaymentRepo repo;
	public void savePayment(List<Payment> pay) {
		
		repo.saveAll(pay);
	}
	public Payment savePayment(Payment payment) {
		
	return	repo.save(payment);
	}
	
	

}
