package com.jsp.swasthik.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.swasthik.dto.Hospital;

public interface HospitalRepo extends JpaRepository<Hospital, String> {
	@Query("select h from Hospital h where h.email=?1")
	Hospital fetchByEmail(String email);
	@Query("select h from Hospital h where h.id=?1")
	Hospital fetchById(String hsptl_id);
	

}
