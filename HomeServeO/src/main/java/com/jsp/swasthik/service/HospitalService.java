package com.jsp.swasthik.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.swasthik.dao.DoctorDao;
import com.jsp.swasthik.dao.HospitalDao;
import com.jsp.swasthik.dto.Doctor;
import com.jsp.swasthik.dto.Hospital;
import com.jsp.swasthik.exception.AccountNotFoundException;
import com.jsp.swasthik.exception.AdminNotFoundException;
import com.jsp.swasthik.exception.HospitalNotFoundException;
import com.jsp.swasthik.exception.NoDoctorFoundException;
import com.jsp.swasthik.util.ResponseStructure;

@Service
public class HospitalService {

	@Autowired
	private HospitalDao hspDao;
	@Autowired
	private DoctorDao doctordao;
	@Autowired
	private JavaMailSender mailSender;

	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(String doctor_id, Hospital hospital) {
		Doctor doctor = doctordao.fetchById(doctor_id);
		if (doctor != null) {
			if (doctor.getAdmin().equalsIgnoreCase("ADMIN")) {
				List<Doctor> list = new ArrayList<Doctor>();
				list.add(doctor);
				hospital.setDoctors(list);
				Hospital hsptl = hspDao.saveHospital(hospital);
				sendMail(doctor.getEmail());
				ResponseStructure<Hospital> structure = new ResponseStructure<Hospital>();
				structure.setData(hsptl);
				structure.setMessage("Hospital Saved Sucessfully");
				structure.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<Hospital>>(structure, HttpStatus.ACCEPTED);
			}
			throw new AdminNotFoundException();
		}
		throw new NoDoctorFoundException();
	}

	public void sendMail(String doctor_email) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("swasthaoffice@gamil.com");
		mail.setSubject("Hospital Registetion");
		mail.setText("you have sucessfully created hospital");
		mail.setTo(doctor_email);
		mailSender.send(mail);
	}

	public ResponseEntity<ResponseStructure<Hospital>> updateHospital(String doctor_id, Hospital hospital) {
		Doctor doctor = doctordao.fetchById(doctor_id);
		if (doctor != null) {
			if (doctor.getSpeciality().equalsIgnoreCase("admin")) {
				Hospital hsptl = hspDao.updateHospital(hospital);
				if (hsptl != null) {
					ArrayList<Doctor> list = new ArrayList<Doctor>();
					list.add(doctor);
					hsptl.setDoctors(list);
					ResponseStructure<Hospital> structure = new ResponseStructure<Hospital>();
					structure.setData(hsptl);
					structure.setMessage("Hospital Updated Sucessfully....");
					structure.setStatus(HttpStatus.FOUND.value());
					return new ResponseEntity<ResponseStructure<Hospital>>(structure, HttpStatus.FOUND);
				}

				throw new HospitalNotFoundException();

			}
			throw new AdminNotFoundException();
		}
		throw new NoDoctorFoundException();

	}

	public ResponseEntity<ResponseStructure<String>> deleteHospital(String doctor_id, String hospital_id) {
		Doctor doctor = doctordao.fetchById(doctor_id);
		if (doctor != null) {
			if (doctor.getSpeciality().equalsIgnoreCase("admin")) {
				hspDao.deleteHospital(hospital_id);
				ResponseStructure<String> structure = new ResponseStructure<String>();
				structure.setMessage("Hospital Deleted Sucessfully..");

				return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.FOUND);
			} else {
				throw new AdminNotFoundException();
			}
		} else {
			throw new NoDoctorFoundException();
		}

	}

	public ResponseEntity<ResponseStructure<Hospital>> addDoctor(String hspt_id, String doctor_id) {
		Hospital hsptl = hspDao.addDoctor(hspt_id, doctor_id);
		if (hsptl != null) {
			ResponseStructure<Hospital> structure = new ResponseStructure<Hospital>();
			structure.setData(hsptl);
			structure.setMessage("Hospital Added Sucessfully....");
			structure.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Hospital>>(structure, HttpStatus.ACCEPTED);
		}
		throw new AccountNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Hospital>> login(String email, String password) {
		Hospital hsptl = hspDao.fetchHospital(email);
		if (hsptl != null) {
			if (hsptl.getPassword().equals(password)) {
				ResponseStructure<Hospital> structure = new ResponseStructure<Hospital>();
				structure.setData(hsptl);
				structure.setMessage("Login Sucessfull");
				structure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Hospital>>(structure, HttpStatus.OK);
			}
		}
		throw new AccountNotFoundException();

	}

	public ResponseEntity<ResponseStructure<Hospital>> registerDoctor(Doctor doctor, String hsptlId) {
		Doctor doc = doctordao.save(doctor);
		Hospital hsptl = hspDao.fetchHospitalById(hsptlId);
		Hospital n_hsptl = hspDao.addDoctor(hsptl.getId(), doc.getId());
		ResponseStructure<Hospital> structure = new ResponseStructure<Hospital>();
		structure.setData(n_hsptl);
		structure.setMessage("Doctor Added Sucessfully");
		structure.setStatus(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<Hospital>>(structure, HttpStatus.OK);
	}

}
