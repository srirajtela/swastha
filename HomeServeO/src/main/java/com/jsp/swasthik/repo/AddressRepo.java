package com.jsp.swasthik.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.swasthik.dto.Address;
@Repository
public interface AddressRepo extends JpaRepository<Address, String> {
	

}
