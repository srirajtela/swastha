package com.jsp.swasthik.exception;

import lombok.Data;

@Data
public class AdminNotFoundException extends RuntimeException {
	private String msg = "Admin Not Found";

	public AdminNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public AdminNotFoundException() {
		super();
	}
	
	
}
