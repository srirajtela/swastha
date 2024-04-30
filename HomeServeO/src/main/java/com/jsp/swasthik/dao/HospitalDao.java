package com.jsp.swasthik.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.swasthik.dto.Address;
import com.jsp.swasthik.dto.Doctor;
import com.jsp.swasthik.dto.Hospital;
import com.jsp.swasthik.repo.HospitalRepo;

@Repository
public class HospitalDao {

	@Autowired
	private HospitalRepo hspRepo;
	@Autowired
	private AddressDao addDao;

	@Autowired
	private DoctorDao doctorDao;

	public Hospital saveHospital(Hospital hospital) {
	Address address = addDao.save(hospital.getAddress());
	hospital.setAddress(address);
		return hspRepo.save(hospital);

	}

	public Hospital updateHospital(Hospital hospital) {
		Hospital hsptl = hspRepo.fetchByEmail(hospital.getEmail());
		if (hsptl != null) {
			if (hospital.getFounder() != null) {
				hsptl.setFounder(hospital.getFounder());
			}
			if (hospital.getName() != null) {
				hsptl.setName(hospital.getName());
			}
			if (hospital.getPhone() != null) {
				hsptl.setPhone(hospital.getPhone());
			}
			if (hospital.getFounder() != null) {
				hsptl.setFounder(hospital.getFounder());
			}
			if (hospital.getWebsite() != null) {
				hsptl.setWebsite(hospital.getWebsite());
			}
			if (hospital.getAddress() != null) {
				hsptl.setAddress(hospital.getAddress());
				addDao.save(hospital.getAddress());
			}
			if (hospital.getDoctors() != null) {
				hsptl.setDoctors(hospital.getDoctors());
			}
			return saveHospital(hsptl);
		}
		return null;

	}

	public void deleteHospital(String hospital_id) {

		hspRepo.deleteById(hospital_id);

	}

	public Hospital addDoctor(String hsptl_id, String doctor_id) {
		Hospital hsptl = hspRepo.fetchById(hsptl_id);
		if (hsptl != null) {
			Doctor doctor = doctorDao.fetchById(doctor_id);
			if (doctor != null) {
				List<Doctor> old_list = hsptl.getDoctors();
				List<Doctor> list = new ArrayList<Doctor>();
				list.add(doctor);
				old_list.addAll(list);
				hsptl.setDoctors(old_list);
				return saveHospital(hsptl);

			}

		}
		return null;

	}

	public Hospital fetchHospital(String email) {
		return hspRepo.fetchByEmail(email);
		
	}
	public Hospital fetchHospitalById(String hsptl_id) {
		return  hspRepo.findById(hsptl_id).get();
	}

}
