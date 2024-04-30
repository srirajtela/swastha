package com.jsp.swasthik.dto;

import java.sql.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jsp.swasthik.util.CustomIdGenerator;

import lombok.Data;

@Data
@Entity
public class AppointmentDate {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "date_seq")
	@GenericGenerator(name = "date_seq", strategy = "com.jsp.swasthik.util.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "AppointmentDate_"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;
	private Date appointmentDate;

	@OneToMany(cascade = CascadeType.ALL)
	private List<AppointmentSlot> slots;

}
