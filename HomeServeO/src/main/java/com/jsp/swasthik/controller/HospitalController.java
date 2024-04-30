package com.jsp.swasthik.controller;

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
import com.jsp.swasthik.dto.Hospital;
import com.jsp.swasthik.service.HospitalService;
import com.jsp.swasthik.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE,
		RequestMethod.POST })
public class HospitalController {

	@Autowired
	private HospitalService hspServ;

	@PostMapping("/savehospital")
	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(@RequestParam String doctor_id,
			@RequestBody Hospital hospital) {
		return hspServ.saveHospital(doctor_id, hospital);
	}

	@GetMapping("/loginhospital")
	public ResponseEntity<ResponseStructure<Hospital>> loginHospital(@RequestParam String email, String password) {
		return hspServ.login(email, password);
	}

	@PutMapping("/updatehospital")
	public ResponseEntity<ResponseStructure<Hospital>> updateHospital(@RequestParam String doctor_id,
			@RequestBody Hospital hospital) {
		return hspServ.updateHospital(doctor_id, hospital);
	}

	@DeleteMapping("/deletehospital")
	public ResponseEntity<ResponseStructure<String>> deleteHospital(@RequestParam String doctor_id,
			String hospital_id) {
		return hspServ.deleteHospital(doctor_id, hospital_id);
	}

	@GetMapping("/adddoctor")
	public ResponseEntity<ResponseStructure<Hospital>> addDoctor(@RequestParam String hspt_id, String doctor_id) {
		return hspServ.addDoctor(hspt_id, doctor_id);
	}

	@PostMapping("/registerdoctor")
	public ResponseEntity<ResponseStructure<Hospital>> registerDoctor(@RequestBody Doctor doctor, String hsptlId) {
		return hspServ.registerDoctor(doctor, hsptlId);
	}

}
