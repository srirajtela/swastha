package com.jsp.swasthik.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.swasthik.dto.Address;
import com.jsp.swasthik.dto.AppointmentDate;
import com.jsp.swasthik.dto.Doctor;

import com.jsp.swasthik.repo.DoctorRepo;

@Repository
public class DoctorDao {

	@Autowired
	private DoctorRepo repo;

	@Autowired
	private AddressDao addDao;

	@Autowired
	private AppointmentDateDao app_dao;

	@Autowired
	private AppointmentSlotDao app_slot;

	public Doctor save(Doctor doctor) {
		Address address = addDao.save(doctor.getAddress());
		if (doctor.getDate() != null) {
			AppointmentDate app_date = app_dao.save(doctor.getDate().get(0));
			app_slot.saveSlot(app_date.getSlots());
		}
		doctor.setAddress(address);
		return repo.save(doctor);
	}

	public Doctor fetchDoctorByEmail(String email) {

		return repo.fetchByEmail(email);
	}

	public Doctor fetchAdmin(String email) {

		Doctor doctor = repo.fetchByEmail(email);
		if (doctor.getAdmin().equals("ADMIN")) {
			return doctor;
		}
		return null;
	}

	public Doctor fetchById(String id) {
		return repo.fetchById(id);
	}

	public List<Doctor> search(String speciality) {
		return repo.search(speciality);

	}

	public void deleteById(String id) {
		repo.deleteById(id);

	}

	public List<Doctor> searchByDoctorName(String name) {
		return repo.searchByDoctorName(name);

	}

	public List<Doctor> fetchDoctorByCity(String city) {
		return repo.searchDoctorByCity(city);

	}

	public List<Doctor> searchByExperience(String experience) {
		return repo.searchByExperience(experience);

	}

	public Doctor updateDoctor(Doctor doctor) {
		Doctor db = fetchById(doctor.getId());
		if (db != null) {

			if (doctor.getAddress() != null) {
				db.setAddress(doctor.getAddress());
			}
			if (doctor.getAvailability() != null) {
				db.setAvailability(doctor.getAvailability());
			}
			if (doctor.getEmail() != null) {
				db.setEmail(doctor.getEmail());
			}
			if (doctor.getFirstName() != null) {
				db.setFirstName(doctor.getFirstName());
			}
			if (doctor.getLastName() != null) {
				db.setLastName(doctor.getLastName());
			}
			if (doctor.getGender() != null) {
				db.setGender(doctor.getGender());
			}
			if (doctor.getPhone() != 0) {
				db.setPhone(doctor.getPhone());
			}
			if (doctor.getSpeciality() != null) {
				db.setSpeciality(doctor.getSpeciality());
			}
			if (doctor.getExperience() != null) {
				db.setExperience(doctor.getExperience());
			}
			if (doctor.getAddress() != null) {
				Address address = addDao.save(doctor.getAddress());
				db.setAddress(address);
			}
			return repo.save(db);
		}
		return null;
	}

	public List<Doctor> fetchAll() {
		return repo.findAll();

	}

	public AppointmentDate checkDates(List<AppointmentDate> doctor_dates, AppointmentDate appointment) {
		for (int i = 0; i < doctor_dates.size(); i++) {

			if (doctor_dates.get(i).getAppointmentDate().equals(appointment.getAppointmentDate())) {
				return doctor_dates.get(i);
			}
		}
		return null;

	}

	public AppointmentDate checkAndReturnAppointment(List<AppointmentDate> doctor_appointments,
			AppointmentDate appointment) {
		for (int i = 0; i < doctor_appointments.size(); i++) {
			if (doctor_appointments.get(i).getAppointmentDate().equals(appointment.getAppointmentDate())) {
				return doctor_appointments.get(i);
			}
		}
		return null;

	}
}
