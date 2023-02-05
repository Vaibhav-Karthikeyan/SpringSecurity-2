package com.masai.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.entity.Employee;
import com.masai.app.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class MyController {
	@Autowired
	EmployeeRepository rep;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/user")
	public ResponseEntity<String> commonResourse() {
		String msg = "Welcome user..! It's a non secured resource";
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@GetMapping("/secure/user")
	public ResponseEntity<String> securedResourse() {
		String msg = "It's a secured resource";
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@GetMapping("/admin")
	public ResponseEntity<String> admin() {
		return new ResponseEntity<>("Welcome to Masai App for Admin", HttpStatus.ACCEPTED);
	}

	@PostMapping("/user")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		employee.setPassword(encoder.encode(employee.getPassword()));
		return new ResponseEntity<>(rep.save(employee),HttpStatus.ACCEPTED);
	}
}
