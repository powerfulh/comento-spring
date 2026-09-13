package com.comento.oracleSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OracleSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(OracleSpringBootApplication.class, args);
	}

}