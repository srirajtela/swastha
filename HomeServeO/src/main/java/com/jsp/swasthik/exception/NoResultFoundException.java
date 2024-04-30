package com.jsp.swasthik.exception;

import lombok.Data;

@Data
public class NoResultFoundException extends RuntimeException {
	
	private String msg="No Result Found";

	public NoResultFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public NoResultFoundException() {
		super();
	}
	
	

}
