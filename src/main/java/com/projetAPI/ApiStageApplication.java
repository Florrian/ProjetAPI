package com.projetAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


// @EntityScan("com.model")
// @EnableJpaRepositories("com.repository")
@SpringBootApplication
public class ApiStageApplication{

	public static void main(String[] args) {
		SpringApplication.run(ApiStageApplication.class, args);
	}


}