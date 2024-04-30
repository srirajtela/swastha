package com.jsp.swasthik.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.swasthik.dto.User;
import com.jsp.swasthik.util.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandlerForSwastha {
	@ExceptionHandler(EmailWrongException.class)
	public ResponseEntity<ResponseStructure<String>> wrongEmail(EmailWrongException e){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setMessage("wrong email");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);	
	}
	
	
	@ExceptionHandler(PasswordWrongException.class)
	public ResponseEntity<ResponseStructure<String>> wrongPassword(PasswordWrongException e){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setMessage("wrong Password");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> accountNotFound(AccountNotFoundException e){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setMessage("Account Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> adminNotFound(AdminNotFoundException e){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setMessage("Admin Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(AppointmentNotBookedException.class)
	public ResponseEntity<ResponseStructure<String>> appointmentNotBooked(AppointmentNotBookedException e){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setMessage("Appointment failed");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(HospitalNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> hospitalNotFound(HospitalNotFoundException e){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setMessage("Account Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(NoBloodGroupFoundException.class)
	public ResponseEntity<ResponseStructure<String>> bloodGroupNotFound(NoBloodGroupFoundException e){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setMessage("Blood Group Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(NoDoctorFoundException.class)
	public ResponseEntity<ResponseStructure<String>> doctorNotFound(NoDoctorFoundException e){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setMessage("Doctor Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(NoResultFoundException.class)
	public ResponseEntity<ResponseStructure<String>> resultNotFound(NoResultFoundException e){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setMessage("Account Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> paymentNotFound(PaymentNotFoundException e){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setMessage("Account Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(SlotNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> slotNotFound(SlotNotFoundException e){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setMessage("Account Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> userNotFound(UserNotFoundException e){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setMessage("Account Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> duplicate(SQLIntegrityConstraintViolationException e){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setMessage("You can not perform this operation");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	
	

}
