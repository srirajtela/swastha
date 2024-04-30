package com.jsp.swasthik.exception;

import lombok.Data;

@Data
public class PaymentNotFoundException extends RuntimeException {
	private String msg = "Payment Unsucessfull..";

	public PaymentNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public PaymentNotFoundException() {
		super();
	}
	
	
}
