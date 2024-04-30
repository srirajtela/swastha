package com.jsp.swasthik.dto;

import java.sql.Time;


import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jsp.swasthik.util.CustomIdGenerator;

import lombok.Data;

@Data
@Entity
public class AppointmentSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slot_seq")
	@GenericGenerator(name = "slot_seq", strategy = "com.jsp.swasthik.util.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "Slot_"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;
	private Time slot;

	@OneToOne(cascade = CascadeType.ALL)
	private Payment payment;

}
