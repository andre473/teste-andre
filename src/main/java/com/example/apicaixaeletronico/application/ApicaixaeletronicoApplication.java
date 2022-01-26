package com.example.apicaixaeletronico.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.apicaixaeletronico")
@ComponentScan("com.example.apicaixaeletronico")
@EnableJpaRepositories("com.example.apicaixaeletronico")
public class ApicaixaeletronicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApicaixaeletronicoApplication.class, args);
	}
}
