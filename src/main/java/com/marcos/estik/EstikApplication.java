package com.marcos.estik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class EstikApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstikApplication.class, args);
	}

}
