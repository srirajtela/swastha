package com.jsp.swasthik.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.jsp.swasthik.dto.Address;
import com.jsp.swasthik.dto.User;

import com.jsp.swasthik.repo.UserRepo;

@Repository
public class UserDao {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AddressDao addDao;

	public User saveUser(User user) {
		Address address = addDao.save(user.getAddress());
		user.setAddress(address);
		return userRepo.save(user);

	}

	public User fetchByEmail(String email) {
		User db = userRepo.fetchByEmail(email);
		if (db != null) {
			return db;
		}
		return null;

	}

	public User findById(String id) {
		return userRepo.fetchById(id);

	}

	public List<User> fetchBlood(String blood) {
		List<User> list = userRepo.findBlood(blood);
		if (list != null) {
			return list;
		}
		return null;
	}

	public void deleteById(String id) {

		userRepo.deleteById(id);
	}

	public User updateUser(User user) {

		User db = findById(user.getId());
		if (db != null) {
			if (user.getFirstName() != null) {
				db.setFirstName(user.getFirstName());
			}
			if (user.getLastName() != null) {
				db.setLastName(user.getLastName());
			}
			if (user.getBloodGroup() != null) {
				db.setBloodGroup(user.getBloodGroup());
			}
			if (user.getAvailability() != null) {
				db.setAvailability(user.getAvailability());
			}
			if (user.getGender() != null) {
				db.setGender(user.getGender());
			}
			if (user.getPhone() != 0) {
				db.setPhone(user.getPhone());
			}
			if (user.getEmail() != null) {
				db.setEmail(user.getEmail());
			}
			if (user.getPassword() != null) {
				db.setPassword(user.getPassword());
			}
			if (user.getAddress() != null) {
				db.setAddress(user.getAddress());
				addDao.save(user.getAddress());
			}
			return userRepo.save(db);
		}
		return null;
	}

	public List<User> fetchAll() {
		return userRepo.findAll();

	}

	public List<User> fetchDonorByCity(String city) {
		List<User> list = userRepo.findDonorByCity(city);
		if (list != null) {
			return list;
		}
		return null;

	}

	public User updatePassword(String email, String password) {
	User user = fetchByEmail(email);
	if(user!=null) {
		user.setPassword(password);
	return 	saveUser(user);
	}
		return null;
		
		
	}

}
