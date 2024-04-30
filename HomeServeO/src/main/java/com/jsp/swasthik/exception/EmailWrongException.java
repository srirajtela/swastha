package com.jsp.swasthik.exception;

import lombok.Data;

@Data
public class EmailWrongException extends RuntimeException {
	
	private String msg  = "Wrong email";

	public EmailWrongException(String msg) {
		super();
		this.msg = msg;
	}

	public EmailWrongException() {
		super();
	}
	
	

}
