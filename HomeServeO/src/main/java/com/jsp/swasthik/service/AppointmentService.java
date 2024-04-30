package com.jsp.swasthik.service;

import java.sql.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.jsp.swasthik.dao.AppointmentDateDao;
import com.jsp.swasthik.dao.AppointmentSlotDao;
import com.jsp.swasthik.dao.DoctorDao;
import com.jsp.swasthik.dao.UserDao;
import com.jsp.swasthik.dto.AppointmentDate;
import com.jsp.swasthik.dto.AppointmentSlot;
import com.jsp.swasthik.dto.Doctor;
import com.jsp.swasthik.dto.Payment;
import com.jsp.swasthik.dto.User;
import com.jsp.swasthik.exception.NoDoctorFoundException;

import com.jsp.swasthik.exception.UserNotFoundException;
import com.jsp.swasthik.util.ResponseStructure;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentDateDao appDateDao;
	@Autowired
	private AppointmentSlotDao appSlotDao;

	@Autowired
	private UserDao userDao;
	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private JavaMailSender mailSender;

	public ResponseEntity<ResponseStructure<AppointmentDate>> bookSlot(String user_id, String doctor_id,
			AppointmentDate date) {
		User user = userDao.findById(user_id);
		if (user != null) {
			Doctor doctor = doctorDao.fetchById(doctor_id);
			if (doctor != null) {
				AppointmentDate app = appDateDao.save(date);
				List<AppointmentDate> existing_appointments = doctor.getDate();
				existing_appointments.add(app);
				doctor.setDate(existing_appointments);
				doctorDao.save(doctor);
				List<Payment> old_transaction = user.getPayment();
				Payment new_payment = app.getSlots().get(0).getPayment();
				old_transaction.add(new_payment);
				user.setPayment(old_transaction);
				userDao.saveUser(user);
				
				ResponseStructure<AppointmentDate> structure = new ResponseStructure<AppointmentDate>();
				structure.setData(app);
				structure.setMessage("Appointment Sucessful from If block");
				structure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<AppointmentDate>>(structure, HttpStatus.OK);

			}

			throw new NoDoctorFoundException();
		}
		throw new UserNotFoundException();

	}

	public void userAppointmentMail(User user, Doctor doctor, AppointmentDate appointment) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("swasthaoffice@gmail.com");
		mail.setSubject("Swastha - Appointment Confirmation");
		mail.setText("Dear [" + user.getFirstName() + "],\r\n" + "\r\n"
				+ "Thank you for choosing Swastha for your healthcare needs. Your appointment has been successfully scheduled with [Doctor's Name].\r\n"
				+ "\r\n" + "Appointment Details:\r\n" + "\r\n" + "Date: [" + appointment.getAppointmentDate() + "]\r\n"
				+ "Time: [Time]\r\n" + "Doctor: Dr. [" + doctor.getFirstName() + "]\r\n" + "Specialty: ["
				+ doctor.getSpeciality() + "]\r\n" + "Location: [" + doctor.getAddress() + "]\r\n"
				+ "For virtual appointments, please ensure that you are logged in to your Swastha account a few minutes before the scheduled time. If you've booked an in-person appointment, kindly arrive at the specified location on time.\r\n"
				+ "\r\n"
				+ "If you need to reschedule or have any questions, please contact our support team at support@swastha.com.\r\n"
				+ "\r\n"
				+ "We look forward to assisting you on [Date]. Thank you for trusting Swastha for your healthcare journey.\r\n"
				+ "\r\n" + "Best regards,\r\n" + "The Swastha Team\r\n" + "\r\n"
				+ "Feel free to customize the content based on your application's specific features and branding. Include any additional information that may be relevant to the appointment process for your users.");
		mail.setTo(user.getEmail());
		mailSender.send(mail);
	}

	public void doctorAppointmentMail(Doctor doctor, User user, AppointmentDate appointment) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("swasthaoffice@gmail.com");
		mail.setSubject("Swastha - Appointment Confirmation");
		mail.setText("Dear Dr. [" + doctor.getFirstName() + "],\r\n" + "\r\n"
				+ "A new appointment has been scheduled through Swastha with you. Here are the details:\r\n" + "\r\n"
				+ "Appointment Details:\r\n" + "\r\n" + "Date: [" + appointment.getAppointmentDate() + "]\r\n"
				+ "Time: [Time]\r\n" + "Patient: [" + user.getFirstName() + "]\r\n" + "Contact Email: ["
				+ user.getEmail() + "]\r\n" + "Contact Phone: [" + user.getPhone() + "]\r\n"
				+ "Appointment Type: [In-person]\r\n"
				+ "Please ensure that you are prepared for the appointment. For virtual appointments, log in to your Swastha account a few minutes before the scheduled time. For in-person appointments, be available at your clinic/hospital.\r\n"
				+ "\r\n"
				+ "If there are any changes or if you have questions, please contact the patient directly or inform our support team at support@swastha.com.\r\n"
				+ "\r\n" + "Thank you for being a part of Swastha and providing quality healthcare.\r\n" + "\r\n"
				+ "Best regards,\r\n" + "The Swastha Team\r\n" + "\r\n"
				+ "Feel free to customize this template based on your application's specific features and requirements. Include any additional information or instructions that may be relevant to the appointment process for doctors.");
		mail.setTo(doctor.getEmail());
		mailSender.send(mail);
	}

	public void cancleSlot(String appointmeSlot_id, String slot_id) {
		appDateDao.cancleSlot(appointmeSlot_id, slot_id);

	}

	public ResponseEntity<ResponseStructure<List<AppointmentSlot>>> searchSlot(Date date, String doctor_id) {

		List<AppointmentSlot> slot = appSlotDao.searchSlot(date, doctor_id);
		ResponseStructure<List<AppointmentSlot>> structure = new ResponseStructure<List<AppointmentSlot>>();
		structure.setData(slot);
		structure.setMessage("Avaialable slots are");
		structure.setStatus(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<List<AppointmentSlot>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<AppointmentDate>>> getSlots(String doctor_id) {
		Doctor doctor = doctorDao.fetchById(doctor_id);
		List<AppointmentDate> slots = doctor.getDate();
		ResponseStructure<List<AppointmentDate>> structure = new ResponseStructure<List<AppointmentDate>>();
		structure.setData(slots);
		structure.setMessage("Available slots are :");
		structure.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<AppointmentDate>>>(structure, HttpStatus.OK);
	}

}