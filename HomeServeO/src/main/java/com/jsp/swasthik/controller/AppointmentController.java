package com.jsp.swasthik.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.swasthik.dto.AppointmentDate;
import com.jsp.swasthik.dto.AppointmentSlot;
import com.jsp.swasthik.service.AppointmentService;
import com.jsp.swasthik.service.DoctorService;
import com.jsp.swasthik.util.ResponseStructure;
@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.POST, RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
public class AppointmentController {
	@Autowired
	private AppointmentService appServ;
	@Autowired
	private DoctorService docServ;
	
	@PostMapping("/bookslot")
	public ResponseEntity<ResponseStructure<AppointmentDate>> bookSlot(@RequestParam String user_id, String doctor_id, @RequestBody AppointmentDate date){
	return	appServ.bookSlot(user_id,doctor_id,date);
	}
	
	@DeleteMapping("/cancleslot")
	public void cancleSlot(@RequestParam String appointmentdate_id, String slot_id){
		 appServ.cancleSlot(appointmentdate_id,slot_id);
	}
	
	@GetMapping("/fetchappointments")
	public ResponseEntity<ResponseStructure<List<AppointmentDate>>> fetchAppointments(@RequestParam String id){
		return docServ.fetchAppointmentsById(id);
	}
	@GetMapping("/searchslot")
	public ResponseEntity<ResponseStructure<List<AppointmentSlot>>> searchDate(@RequestParam Date date, String doctor_id){
		return appServ.searchSlot(date,doctor_id);
	}
	
	@GetMapping("/getslots")
	public ResponseEntity<ResponseStructure<List<AppointmentDate>>> getslots(@RequestParam String doctor_id){
		return appServ.getSlots(doctor_id);
	}
	

}
