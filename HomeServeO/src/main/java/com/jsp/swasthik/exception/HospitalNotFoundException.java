package com.jsp.swasthik.exception;

import lombok.Data;

@Data
public class HospitalNotFoundException extends RuntimeException {
	private String msg = "Hospital Not Found";

	public HospitalNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public HospitalNotFoundException() {
		super();
	}
	
	
}
