package com.jsp.swasthik.exception;

import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException{
	private String msg = "No Account found with ID";

	public UserNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public UserNotFoundException() {
		super();
	}
	
	
}
