package com.jsp.swasthik.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.swasthik.dto.AppointmentDate;
import com.jsp.swasthik.dto.AppointmentSlot;
import com.jsp.swasthik.repo.AppointmentDateRepo;

@Repository
public class AppointmentDateDao {

	@Autowired
	private AppointmentDateRepo appDateRepo;

	public AppointmentDate bookSlot(AppointmentDate date) {
		AppointmentDate existing = appDateRepo.searchDate(date.getAppointmentDate());
		if (existing != null) {
			return null;
		}
		else
		return appDateRepo.save(date);

	}

	public void cancleSlot(String appointmentdate_id, String slot_id) {
		// appDateRepo.deleteAppointment(slot_id);
	}

	public AppointmentDate findDate(Date appointmentDate) {
		return appDateRepo.searchDate(appointmentDate);

	}

	public List<AppointmentDate> fetchAll() {
		return appDateRepo.findAll();

	}

	public boolean checkSlot(AppointmentDate appointment, AppointmentDate date) {
		List<AppointmentSlot> existing = appointment.getSlots();
		Time slot = date.getSlots().get(0).getSlot();
		for (int i = 0; i < existing.size(); i++) {
			if (existing.get(i).getSlot().equals(slot)) {
				return false;
			}
		}

		return true;

	}

	public AppointmentDate save(AppointmentDate appointment) {
		return appDateRepo.save(appointment);

	}

}