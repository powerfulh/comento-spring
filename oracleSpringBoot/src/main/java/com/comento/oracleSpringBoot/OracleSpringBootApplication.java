package com.comento.oracleSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@PropertySource("classpath:password.properties")
@EnableAsync
@EnableConfigurationProperties({PropVo.class})
public class OracleSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(OracleSpringBootApplication.class, args);
	}

}