package com.jsp.swasthik.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.swasthik.dto.Address;
import com.jsp.swasthik.repo.AddressRepo;
@Repository
public class AddressDao {
	
	@Autowired
	private AddressRepo repo;
	public Address save(Address address) {
		return repo.save(address);
		
	}

}
