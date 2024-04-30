package com.jsp.swasthik.exception;

import lombok.Data;

@Data
public class AppointmentNotBookedException extends RuntimeException{
	private String msg = "AppointMent Not Placed ";

	public AppointmentNotBookedException(String msg) {
		super();
		this.msg = msg;
	}

	public AppointmentNotBookedException() {
		super();
	}
	
	
}
