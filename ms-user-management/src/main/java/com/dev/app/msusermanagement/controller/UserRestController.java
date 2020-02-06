package com.dev.app.msusermanagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.app.msusermanagement.entity.User;
import com.dev.app.msusermanagement.enums.Role;
import com.dev.app.msusermanagement.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@PostMapping("/service/registration")
	ResponseEntity<?> saveUser(@RequestBody User user) {
		if (userService.findByUsername(user.getUsername()) != null) {
			//Status code 409
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		//Default Role = USER
		user.setRole(Role.USER);
		User userReturn = userService.save(user);
		return new ResponseEntity<>(userReturn, HttpStatus.CREATED);
	}
	
	@GetMapping("/service/login")
	ResponseEntity<?> getUser(Principal principal) {
		if (principal == null || principal.getName() == null) {
			//this means; logout will be successful
			return new ResponseEntity<>(HttpStatus.OK);
		}
		User user = userService.findByUsername(principal.getName());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping("/service/name")
	ResponseEntity<?> getNameOfUsers(@RequestBody List<Long> idList) {
		List<String> userNames = userService.findUsers(idList);
		return new ResponseEntity<>(userNames, HttpStatus.OK);
	}
	
	@GetMapping("/service/test")
	ResponseEntity<?> test() {
		return new ResponseEntity<>("It is working", HttpStatus.OK);
	}
}
