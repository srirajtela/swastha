package com.jsp.swasthik.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jsp.swasthik.util.CustomIdGenerator;

import lombok.Data;

@Data
@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
	@GenericGenerator(name = "doctor_seq", strategy = "com.jsp.swasthik.util.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "Doctor_"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private String password;
	private String Speciality;
	private String experience;
	private String availability;
	private long phone;
	private double fee;
	private String gender;
	private String admin;

	@OneToOne
	private Address address;

	@OneToMany
	private List<AppointmentDate> date;

	@ManyToMany
	@JoinColumn
	private List<User> patient;

}
