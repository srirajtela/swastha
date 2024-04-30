package com.jsp.swasthik.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jsp.swasthik.util.CustomIdGenerator;

import lombok.Data;
@Data
@Entity
public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq")
	@GenericGenerator(name = "hospital_seq", strategy = "com.jsp.swasthik.util.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "Hospital_"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;
	private String name;
	private String founder;
	@Column(unique = true)
	private String email;
	private String password;
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFounder() {
		return founder;
	}


	public void setFounder(String founder) {
		this.founder = founder;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public List<Doctor> getDoctors() {
		return doctors;
	}


	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}


	private String phone;
	private String website;
	@OneToOne
	private Address address;
	
	
	@OneToMany
	private List<Doctor> doctors;

}
