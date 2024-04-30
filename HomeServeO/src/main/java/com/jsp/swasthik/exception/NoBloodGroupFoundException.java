package com.jsp.swasthik.exception;

import lombok.Data;

@Data
public class NoBloodGroupFoundException extends RuntimeException{
	private String msg = "No Blood found ";

	public NoBloodGroupFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public NoBloodGroupFoundException() {
		super();
	}
	
	
}
