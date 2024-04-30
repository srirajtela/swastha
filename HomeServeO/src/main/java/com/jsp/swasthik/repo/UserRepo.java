package com.jsp.swasthik.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.jsp.swasthik.dto.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

	@Query("select u from User u where u.email=?1")
	public User fetchByEmail(String email);

	@Query("select u from User u where u.bloodGroup=?1 and u.availability='AVAILABLE'")
	public List<User> findBlood(String blood);

	@Query("select u from User u where u.id=?1")
	public User fetchById(String id);

	@Query("select u from User u where u.address.city=?1")
	public List<User> findDonorByCity(String city);

}
