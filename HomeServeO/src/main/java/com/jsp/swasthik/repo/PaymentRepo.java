package com.jsp.swasthik.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.swasthik.dto.Payment;

public interface PaymentRepo extends JpaRepository<Payment, String> {

}
