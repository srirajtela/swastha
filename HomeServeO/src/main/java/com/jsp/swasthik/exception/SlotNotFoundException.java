package com.jsp.swasthik.exception;

import lombok.Data;

@Data
public class SlotNotFoundException extends RuntimeException{
	private String msg = "Slot Doesn't Exist";

	public SlotNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public SlotNotFoundException() {
		super();
	}
	
	
}
