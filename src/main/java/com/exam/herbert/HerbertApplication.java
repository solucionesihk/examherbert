package com.exam.herbert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.exam.herbert.bussiness", "com.exam.herbert.controller", "com.exam.herbert.config", "com.exam.herbert.model.response", "com.exam.herbert.restclient", "com.exam.herbert.util"})
public class HerbertApplication {

	public static void main(String[] args) {
		SpringApplication.run(HerbertApplication.class, args);
	}

}
