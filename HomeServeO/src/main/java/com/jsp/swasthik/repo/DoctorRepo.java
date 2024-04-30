package com.jsp.swasthik.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.swasthik.dto.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, String>{
	@Query("select d from Doctor d where d.email=?1")
	Doctor fetchByEmail(String email);
	@Query("select d from Doctor d where d.Speciality=?1 and d.availability='AVAILABLE'")
	List<Doctor> search(String speciality);
	
	@Query("select d from Doctor d where d.firstName=?1")
	List<Doctor> searchByDoctorName(String name);
	
	@Query("select d from Doctor d where d.address.city=?1")
	List<Doctor> searchDoctorByCity(String city);
	
	@Query("select d from Doctor d where d.experience=?1")
	List<Doctor> searchByExperience(String experience);
	@Query("select d from Doctor d where d.id=?1")
	Doctor fetchById(String id);

}
