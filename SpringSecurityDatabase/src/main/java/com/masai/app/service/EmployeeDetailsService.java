package com.masai.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.app.entity.Employee;
import com.masai.app.repository.EmployeeRepository;

@Service
public class EmployeeDetailsService implements UserDetailsService {
	@Autowired
	EmployeeRepository rep;

	EmployeeDetails user = null;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = rep.findByUsername(username);
		if (employee != null) {
			user = new EmployeeDetails(employee);
		}
	return user;
	}
}
