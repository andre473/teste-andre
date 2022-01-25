package com.example.apicaixaeletronico.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@SpringBootApplication
@ComponentScan("com.example.apicaixaeletronico")
public class ApicaixaeletronicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApicaixaeletronicoApplication.class, args);
	}

}
