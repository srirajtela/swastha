package com.jsp.swasthik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.swasthik.dto.Doctor;
import com.jsp.swasthik.service.DoctorService;
import com.jsp.swasthik.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.POST, RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
public class DoctorController {
	
	@Autowired
	private DoctorService service;
	
	@PostMapping("/savedoctor")
	public ResponseEntity<ResponseStructure<Doctor>> save(@RequestBody Doctor doc){
		return service.save(doc);
	}
	
	@GetMapping("/doctorlogin")
	public ResponseEntity<ResponseStructure<Doctor>> doctorLogin(@RequestParam String email, String password){
		return service.doctorLogin(email,password);
	}
	
	@GetMapping("/adminlogin")
	public ResponseEntity<ResponseStructure<Doctor>> adminLogin(@RequestParam String email, String password){
		return service.adminLogin(email,password);
	}
	
	@GetMapping("/doctorotp")
	public ResponseEntity<ResponseStructure<Integer>> forgotPassword (@RequestParam String email){
		return service.otp(email);
	}
	
	@GetMapping("/doctorfetch")
	public ResponseEntity<ResponseStructure<Doctor>> fetchById(@RequestParam String id){
		return service.fetchById(id);
	}
	
	@GetMapping("/searchdoctorspeciality")
	public ResponseEntity<ResponseStructure<List<Doctor>>> serachdoctor(@RequestParam String speciality){
		return service.serachDoctor(speciality);
	}
	
	
	@GetMapping("/searchbyname")
	public ResponseEntity<ResponseStructure<List<Doctor>>> searchByName(@RequestParam String name){
		return service.searchDoctorByName(name);
	}
	@GetMapping("/searchbycity")
	public ResponseEntity<ResponseStructure<List<Doctor>>> searchByCity(@RequestParam String city){
		return service.searchByCity(city);
	}
	@GetMapping("/searchbyexperience")
	public ResponseEntity<ResponseStructure<List<Doctor>>> searchByExperince(@RequestParam String experience){
		return service.searchByExperience(experience);
	}
	
	@GetMapping("/fetchalldoctor")
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchAll(){
		return service.fetchAll();
	}
	@DeleteMapping("/deletedoctor")
	public ResponseEntity<ResponseStructure<String>> deleteDoctorById(@RequestParam String id){
		return service.deleteDoctor(id);
	}
	@PutMapping("/updatedoctor")
	public ResponseEntity<ResponseStructure<Doctor>> updateDoctor(@RequestBody Doctor doctor){
		return service.update(doctor);
	}
	
	
	
	
	
	

}
