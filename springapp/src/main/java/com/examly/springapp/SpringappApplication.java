package com.examly.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringappApplication {
	// @Bean
	// public PasswordEncoder createEncoder(){
	// 	return new BCryptPasswordEncoder();
	// }
	public static void main(String[] args) {
		SpringApplication.run(SpringappApplication.class, args);
	}

}
