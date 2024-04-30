package com.jsp.swasthik.exception;

import lombok.Data;

@Data
public class AccountNotFoundException extends RuntimeException {
	
	private String msg = "No Account found with ID";

	public AccountNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public AccountNotFoundException() {
		super();
	}
	
	

}
