package com.loja3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Loja3Application {

	public static void main(String[] args) {
		SpringApplication.run(Loja3Application.class, args);
		
		//System.out.println(new BCryptPasswordEncoder().encode("123"));
		
	}

}
