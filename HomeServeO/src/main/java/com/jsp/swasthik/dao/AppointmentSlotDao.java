package com.jsp.swasthik.dao;

import java.sql.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.jsp.swasthik.dto.AppointmentSlot;
import com.jsp.swasthik.repo.AppointmentSlotRepo;

@Repository
public class AppointmentSlotDao {
	@Autowired
	private AppointmentSlotRepo slotRepo;

	public List<AppointmentSlot> saveSlot(List<AppointmentSlot> slot) {
		return slotRepo.saveAll(slot);

	}

	public void cancleSlot(String appointmeSlot_id) {
		slotRepo.deleteById(appointmeSlot_id);

	}

	public AppointmentSlot fetchAppointment(String slot_id) {
		return slotRepo.fetchById(slot_id);

	}

	public List<AppointmentSlot> searchSlot(Date appointmentDate, String doctor_id) {
		return null;
	}

}
