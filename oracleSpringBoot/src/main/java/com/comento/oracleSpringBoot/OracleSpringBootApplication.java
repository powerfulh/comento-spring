package com.comento.oracleSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@PropertySource("classpath:password.properties")
@EnableAsync
public class OracleSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(OracleSpringBootApplication.class, args);
	}

}
