package com.masai.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	protected SecurityFilterChain masaiSecurityConfig(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests( (auth)->auth
				.requestMatchers("/api/user").permitAll()
				.requestMatchers("/api/admin").hasAuthority("admin")
				.requestMatchers("/api/secure/user").authenticated()
		).csrf().disable().httpBasic();
	
		return http.build();
	}
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	 }
}


