package com.jsp.swasthik.dto;

import java.util.List;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsp.swasthik.util.CustomIdGenerator;

import javax.persistence.*;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@GenericGenerator(name = "user_seq", strategy = "com.jsp.swasthik.util.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "User_"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private String password;
	private String bloodGroup;
	private String availability;
	private long phone;
	private String gender;
	@OneToOne
	private Address address;
	@OneToMany
	private List<Payment> payment;
	@JsonIgnore
	@ManyToMany(mappedBy = "patient")
	private List<Doctor> doctor;

}
