package com.jsp.swasthik.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import com.jsp.swasthik.dao.UserDao;

import com.jsp.swasthik.dto.User;
import com.jsp.swasthik.exception.AccountNotFoundException;
import com.jsp.swasthik.exception.EmailWrongException;

import com.jsp.swasthik.exception.PasswordWrongException;

import com.jsp.swasthik.util.ResponseStructure;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private JavaMailSender mailSender;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<User>();
		structure.setData(userDao.saveUser(user));
		structure.setMessage("user saved Sucessfully");
		structure.setStatus(HttpStatus.OK.value());

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("swasthaoffice@gmail.com");
		msg.setSubject("Welcome to Swastha - Your Health, Our Priority!");
		msg.setTo(user.getEmail());
		msg.setText("Dear [" + user.getFirstName() + "],\r\n" + "\r\n"
				+ "Thank you for registering with Swastha, your trusted healthcare companion. We are excited to have you on board! Your health and well-being are our top priorities."
				+ "\r\n" + "To get started, simply log in to your Swastha account using your registered email (["
				+ user.getEmail() + "]) and the password you created during registration.\r\n" + "\r\n"
				+ "If you have any questions or need assistance, feel free to reach out to our support team at support@swastha.com.\r\n"
				+ "\r\n"
				+ "Thank you for choosing Swastha. We look forward to being a part of your health and wellness journey.\r\n"
				+ "\r\n" + "Best regards,\r\n" + "The Swastha Team\r\n" + "\r\n"
				+ "Feel free to customize the content based on your application's specific features and branding. Additionally, include any relevant contact information or links to support resources in the email.\r\n"
				+ "\r\n");

		mailSender.send(msg);

		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> login(String email, String password) {
		User db = userDao.fetchByEmail(email);
		if (db != null) {
			if (db.getPassword().equals(password)) {
				ResponseStructure<User> structure = new ResponseStructure<User>();
				structure.setData(db);
				structure.setMessage("login Sucessfull");
				structure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			}
			throw new PasswordWrongException("Password incorrect");
		}
		throw new EmailWrongException("wrong email " + email);
	}

	public ResponseEntity<ResponseStructure<Integer>> forgotPassword(String email) {
		User db = userDao.fetchByEmail(email);
		ResponseStructure<Integer> structure = new ResponseStructure<Integer>();
		if (db != null) {
			Random random = new Random();
			int otp = 1000+random.nextInt(9000);
			structure.setData(otp);
			structure.setMessage("otp verification");
			structure.setStatus(HttpStatus.OK.value());

			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("swasthaoffice@gmail.com");
			msg.setSubject("Verification Code");
			msg.setText("Dear [" + db.getFirstName() + "],\r\n" + "\r\n"
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
			msg.setTo(email);
			mailSender.send(msg);
			return new ResponseEntity<ResponseStructure<Integer>>(structure, HttpStatus.OK);
		}
		throw new AccountNotFoundException("Account not found ");
	}

	public ResponseEntity<ResponseStructure<User>> findById(String id) {
		User db = userDao.findById(id);
		if (db != null) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setData(db);
			structure.setMessage("fetched sucessfully..");
			structure.setStatus(HttpStatus.FOUND.value());

			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
		}
		throw new AccountNotFoundException("User Not Found");

	}

	public ResponseEntity<ResponseStructure<List<User>>> fetchBlood(String blood) {
		List<User> list = userDao.fetchBlood(blood);
		ResponseStructure<List<User>> structure = new ResponseStructure<List<User>>();
		structure.setData(list);
		structure.setMessage("Results are ");
		structure.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<String>> deleteById(String id) {
		User db = userDao.findById(id);
		if (db != null) {
			userDao.deleteById(id);
			ResponseStructure<String> structure = new ResponseStructure<String>();
			structure.setMessage("Account Deleted Sucessfully....");
			structure.setStatus(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new AccountNotFoundException();

	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		User db = userDao.updateUser(user);
		if (db != null) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setData(db);
			structure.setMessage("User Updated Sucessfully");
			structure.setStatus(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);

		}

		throw new AccountNotFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> sendMessage(String from, String to) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject("Blood Requirement");
		mail.setText("Dear Donor,\r\n" + "\r\n"
				+ "I hope this message finds you well. We are reaching out to you with an urgent and heartfelt appeal for blood donation. A patient in our care is currently in critical condition and requires immediate blood transfusion to sustain life.\r\n"
				+ "\r\n"
				+ "Your selfless act of donating blood can make a significant difference and be the lifeline that this patient desperately needs. We understand that your time is valuable, but your generosity can help save a life and bring hope to both the patient and their loved ones.\r\n"
				+ "\r\n"
				+ "If you are able and willing to donate blood, please visit our [Hospital/Clinic Name] located at [Address]. Our medical staff is ready to assist you through the donation process and ensure a safe and comfortable experience.\r\n"
				+ "\r\n"
				+ "Donating blood is a noble and compassionate gesture that can have a profound impact on someone's life. Your support will be deeply appreciated, and you will forever be a hero in the eyes of those whose lives you touch.\r\n"
				+ "\r\n"
				+ "If you have any questions or concerns, please feel free to contact us at [Your Contact Information].\r\n"
				+ "\r\n"
				+ "Thank you for considering this urgent appeal. Your kindness and generosity can make a world of difference.\r\n"
				+ "\r\n" + "Best regards,");
		mailSender.send(mail);

		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Message Sent Sucessfullyyy.....");
		structure.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<List<User>>> fetchAll() {
		List<User> list = userDao.fetchAll();
		ResponseStructure<List<User>> structure = new ResponseStructure<List<User>>();
		structure.setData(list);
		structure.setMessage("Found");
		structure.setStatus(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<User>>> fetchDonorByCity(String city) {
		List<User> list = userDao.fetchDonorByCity(city);
		ResponseStructure<List<User>> structure = new ResponseStructure<List<User>>();
		structure.setData(list);
		structure.setMessage("Results are ");
		structure.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<User>> updatePassword(String email, String password) {
	User user = userDao.updatePassword(email,password);
	if (user != null) {
		ResponseStructure<User> structure = new ResponseStructure<User>();
		structure.setData(user);
		structure.setMessage("fetched sucessfully..");
		structure.setStatus(HttpStatus.FOUND.value());

		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
	}
	throw new AccountNotFoundException("User Not Found");
	}

}
