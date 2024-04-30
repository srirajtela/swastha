package com.jsp.swasthik.exception;

import lombok.Data;

@Data
public class PasswordWrongException extends RuntimeException{
	
	private String msg = "Wrong Password";

	public PasswordWrongException(String msg) {
		super();
		this.msg = msg;
	}

	public PasswordWrongException() {
		super();
	}
	
	

}
