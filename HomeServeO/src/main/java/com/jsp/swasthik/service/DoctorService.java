package com.jsp.swasthik.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.swasthik.dao.DoctorDao;

import com.jsp.swasthik.dto.AppointmentDate;
import com.jsp.swasthik.dto.Doctor;

import com.jsp.swasthik.exception.AccountNotFoundException;
import com.jsp.swasthik.exception.AdminNotFoundException;

import com.jsp.swasthik.exception.NoDoctorFoundException;
import com.jsp.swasthik.exception.NoResultFoundException;
import com.jsp.swasthik.exception.PasswordWrongException;
import com.jsp.swasthik.util.ResponseStructure;

@Service
public class DoctorService {

	@Autowired
	private DoctorDao dao;
	@Autowired
	private JavaMailSender mailSender;

	public ResponseEntity<ResponseStructure<Doctor>> save(Doctor doctor) {

		ResponseStructure<Doctor> structure = new ResponseStructure<Doctor>();
		structure.setData(dao.save(doctor));
		structure.setMessage("doctor Registration Sucessfull....");
		structure.setStatus(HttpStatus.ACCEPTED.value());

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("swasthaoffice@gmail.com");
		mail.setSubject("Welcome to Swastha - Joining the Healthcare Revolution!");
		mail.setTo(doctor.getEmail());
		mail.setText("Dear Dr. [" + doctor.getFirstName() + "],\r\n" + "\r\n"
				+ "Congratulations and welcome to Swastha! We are thrilled to have you as a part of our esteemed network of healthcare professionals. Your commitment to healthcare excellence aligns perfectly with our mission to provide accessible and quality healthcare to all.\r\n"
				+ "\r\n" + "Here are some key features available to you on Swastha:\r\n" + "\r\n"
				+ "Appointment Management: Easily manage and update your appointment availability.\r\n"
				+ "Profile Customization: Enhance your professional profile to showcase your expertise.\r\n"
				+ "Secure Communication: Communicate with patients securely through our platform.\r\n"
				+ "To access your Swastha doctor account, please log in using your registered email ([Doctor's Email]) and the password you created during registration.\r\n"
				+ "\r\n"
				+ "If you have any questions or require assistance, our support team is here to help. Reach out to us at support@swastha.com.\r\n"
				+ "\r\n"
				+ "Thank you for choosing Swastha. Together, we can make a significant impact on healthcare accessibility and patient care.\r\n"
				+ "\r\n" + "Best regards,\r\n" + "The Swastha Team\r\n" + "\r\n"
				+ "Feel free to adjust the content based on your specific platform features and branding. Additionally, include any relevant contact information or links to support resources for doctors.\r\n"
				+ "\r\n");
		mailSender.send(mail);

		return new ResponseEntity<ResponseStructure<Doctor>>(structure, HttpStatus.ACCEPTED);

	}

	public ResponseEntity<ResponseStructure<Doctor>> doctorLogin(String email, String password) {
		ResponseStructure<Doctor> structure = new ResponseStructure<Doctor>();
		Doctor db = dao.fetchDoctorByEmail(email);
		if (db != null) {
			if (db.getPassword().equals(password)) {
				structure.setData(db);
				structure.setMessage("login sucessfulll.....");
				structure.setStatus(HttpStatus.OK.value());

				return new ResponseEntity<ResponseStructure<Doctor>>(structure, HttpStatus.OK);
			}
			throw new PasswordWrongException("Incorrect password");
		}
		throw new NoDoctorFoundException();
	}

	public ResponseEntity<ResponseStructure<Doctor>> adminLogin(String email, String password) {
		ResponseStructure<Doctor> structure = new ResponseStructure<Doctor>();
		Doctor db = dao.fetchDoctorByEmail(email);
		if (db != null) {
			if (db.getPassword().equals(password)) {
				if (db.getAdmin().equals("ADMIN")) {
					structure.setData(db);
					structure.setMessage("login sucessfulll.....");
					structure.setStatus(HttpStatus.OK.value());

					return new ResponseEntity<ResponseStructure<Doctor>>(structure, HttpStatus.OK);
				}
				throw new AdminNotFoundException("Not an ADMIN");
			}
			throw new PasswordWrongException("Incorrect password");
		}
		throw new NoDoctorFoundException();
	}

	public ResponseEntity<ResponseStructure<Integer>> otp(String email) {
		Doctor db = dao.fetchDoctorByEmail(email);
		if (db != null) {
			Random random = new Random();
			int otp = random.nextInt();
			ResponseStructure<Integer> structure = new ResponseStructure<Integer>();
			structure.setData(otp);
			structure.setMessage("Otp sent Sucessfullyyy...");
			structure.setStatus(HttpStatus.OK.value());

			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setFrom("swasthaoffice@gmail.com");
			mail.setSubject("Swastha - Password Reset OTP");
			mail.setText("Dear [" + db.getFirstName() + "],\r\n" + "\r\n"
					+ "You've requested to reset your Swastha account password. To proceed, please use the following OTP (One-Time Password) within the next [time period, 10 minutes]:\r\n"
					+ "\r\n" + "Your OTP: [" + otp + "]\r\n" + "\r\n"
					+ "Enter this OTP on the password reset page to create a new password. Please do not share this OTP with anyone for security reasons.\r\n"
					+ "\r\n"
					+ "If you did not initiate this request, kindly ignore this email. Your account security is our priority.\r\n"
					+ "\r\n"
					+ "For further assistance or if you encounter any issues, contact our support team at support@swastha.com.\r\n"
					+ "\r\n" + "Thank you for choosing Swastha.\r\n" + "\r\n" + "Best regards,\r\n"
					+ "The Swastha Team\r\n" + "\r\n"
					+ "This email informs the user about the OTP and provides clear instructions on how to use it for the password reset process. Adjust the content as needed based on your application's specific requirements and branding.");
			mail.setTo(email);
			mailSender.send(mail);
			return new ResponseEntity<ResponseStructure<Integer>>(structure, HttpStatus.OK);
		}
		throw new AccountNotFoundException("No Account Found With Email");
	}

	public ResponseEntity<ResponseStructure<Doctor>> fetchById(String id) {

		Doctor db = dao.fetchById(id);
		if (db != null) {
			ResponseStructure<Doctor> structure = new ResponseStructure<Doctor>();
			structure.setData(db);
			structure.setMessage("Doctor Found");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Doctor>>(structure, HttpStatus.OK);
		}
		throw new AccountNotFoundException("Doctor Doesnt Exist");
	}

	public ResponseEntity<ResponseStructure<List<Doctor>>> serachDoctor(String speciality) {
		List<Doctor> db = dao.search(speciality);
		if (db != null) {
			ResponseStructure<List<Doctor>> structure = new ResponseStructure<List<Doctor>>();
			structure.setData(db);
			structure.setMessage("Result..");
			structure.setStatus(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<Doctor>>>(structure, HttpStatus.OK);
		}
		throw new NoResultFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> deleteDoctor(String id) {
		Doctor doctor = dao.fetchById(id);
		if (doctor != null) {
			dao.deleteById(id);
			ResponseStructure<String> structure = new ResponseStructure<String>();
			structure.setMessage("Account Deleted Sucessfully...");
			structure.setStatus(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new AccountNotFoundException("Account does't Exist");
	}

	public ResponseEntity<ResponseStructure<Doctor>> update(Doctor doctor) {
		Doctor doc = dao.updateDoctor(doctor);
		if (doc != null) {
			ResponseStructure<Doctor> structure = new ResponseStructure<Doctor>();
			structure.setData(doc);
			structure.setMessage("Update Sucessfull...");
			structure.setStatus(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<Doctor>>(structure, HttpStatus.OK);
		}
		throw new AccountNotFoundException();

	}

	public ResponseEntity<ResponseStructure<List<Doctor>>> searchDoctorByName(String name)  {
		List<Doctor> db = dao.searchByDoctorName(name);
		if (db != null) {
			ResponseStructure<List<Doctor>> structure = new ResponseStructure<List<Doctor>>();
			structure.setData(db);
			structure.setMessage("Results Found are...");
			structure.setStatus(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<Doctor>>>(structure, HttpStatus.OK);
		}

		throw new NoDoctorFoundException();

	}

	public ResponseEntity<ResponseStructure<List<Doctor>>> searchByCity(String city) {
		List<Doctor> db = dao.fetchDoctorByCity(city);
		if (db != null) {
			ResponseStructure<List<Doctor>> structure = new ResponseStructure<List<Doctor>>();
			structure.setData(db);
			structure.setMessage("Results Found are");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Doctor>>>(structure, HttpStatus.OK);
		}
		throw new NoDoctorFoundException();

	}

	public ResponseEntity<ResponseStructure<List<Doctor>>> searchByExperience(String experience) {
		List<Doctor> db = dao.searchByExperience(experience);
		if (db != null) {
			ResponseStructure<List<Doctor>> structure = new ResponseStructure<List<Doctor>>();
			structure.setData(db);
			structure.setMessage("results found are");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Doctor>>>(structure, HttpStatus.OK);
		}
		throw new NoDoctorFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Doctor>>> searchCity(String city) {
		List<Doctor> list = dao.fetchDoctorByCity(city);
		if (list != null) {
			ResponseStructure<List<Doctor>> structure = new ResponseStructure<List<Doctor>>();
			structure.setData(list);
			structure.setMessage("Resulis found are");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Doctor>>>(structure, HttpStatus.OK);
		}
		throw new NoDoctorFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchAll() {
		List<Doctor> list = dao.fetchAll();
		ResponseStructure<List<Doctor>> structure = new ResponseStructure<List<Doctor>>();
		structure.setData(list);
		structure.setMessage("Results found  are ");
		structure.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<AppointmentDate>>> fetchAppointmentsById(String id) {
		Doctor doctor = dao.fetchById(id);
		if (doctor != null) {
			ResponseStructure<List<AppointmentDate>> structure = new ResponseStructure<List<AppointmentDate>>();
			List<AppointmentDate> appointments = doctor.getDate();
			structure.setData(appointments);
			structure.setMessage("Appointments are");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<AppointmentDate>>>(structure, HttpStatus.OK);
		}
		throw new NoDoctorFoundException();
	}

}
