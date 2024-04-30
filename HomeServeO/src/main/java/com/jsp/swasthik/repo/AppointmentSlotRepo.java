package com.jsp.swasthik.repo;

import java.sql.Time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.swasthik.dto.AppointmentSlot;

public interface AppointmentSlotRepo extends JpaRepository<AppointmentSlot, String> {
	@Query("select a from AppointmentSlot a where a.id=?1")
	AppointmentSlot fetchById(String appointmeSlot_id);

	@Query("select a from AppointmentSlot a where a.slot=?1")
	AppointmentSlot searchSlot(Time newslot);

}
