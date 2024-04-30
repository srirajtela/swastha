package com.jsp.swasthik.exception;

import lombok.Data;

@Data
public class NoDoctorFoundException extends RuntimeException{
	private String msg = "No Account found with ID";

	public NoDoctorFoundException() {
		super();
	}

	public NoDoctorFoundException(String msg) {
		super();
		this.msg = msg;
	}
	
}
