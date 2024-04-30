package com.jsp.swasthik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.swasthik.dto.User;
import com.jsp.swasthik.service.UserService;
import com.jsp.swasthik.util.ResponseStructure;


@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.POST, RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
public class UserController {
	
	
	@Autowired
	private UserService userService;

	@PostMapping("/usersave")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user);

	}

	@GetMapping("/userlogin")
	public ResponseEntity<ResponseStructure<User>> userLogin(@RequestParam String email, String password) {
		return userService.login(email,password);
	}

	@GetMapping("/userforgot")
	public ResponseEntity<ResponseStructure<Integer>> forgotPassword(@RequestParam String email) {
		return userService.forgotPassword(email);
	}

	@GetMapping("/userfetch")
	public ResponseEntity<ResponseStructure<User>> findById(@RequestParam String id) {
		return userService.findById(id);
	}

	@GetMapping("/findblood")
	public ResponseEntity<ResponseStructure<List<User>>> fetchBlood(@RequestParam String blood) {
		return userService.fetchBlood(blood);

	}
	@GetMapping("/finddonorbycity")
	public ResponseEntity<ResponseStructure<List<User>>> fetchDonorByCity(@RequestParam String city) {
		return userService.fetchDonorByCity(city);

	}

	@DeleteMapping("/userdelete")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@RequestParam String id) {
		return userService.deleteById(id);
	}

	@PutMapping("/userupdate")
	public ResponseEntity<ResponseStructure<User>> update(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@GetMapping("/sendmessage")
	public ResponseEntity<ResponseStructure<String>> sendMessage(@RequestParam String from, String to){
		return userService.sendMessage(from,to);
	}
	
	@GetMapping("/userfetchall")
	public ResponseEntity<ResponseStructure<List<User>>> fetchAll(){
		return userService.fetchAll();
	}
	@PutMapping("/updatepassword")
	public ResponseEntity<ResponseStructure<User>> updatePassword(@RequestParam String email, String password ){
		return userService.updatePassword(email,password);
		
	}

}
