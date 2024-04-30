package com.jsp.swasthik.repo;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.swasthik.dto.AppointmentDate;

public interface AppointmentDateRepo extends JpaRepository<AppointmentDate, String>{
	@Query("select a from AppointmentDate a where a.id=?1")
	AppointmentDate fetchById(String appointmentdate_id);

	@Query("select d from AppointmentDate d where d.appointmentDate=?1")
	AppointmentDate searchDate(Date appointmentDate);
	
	

}
