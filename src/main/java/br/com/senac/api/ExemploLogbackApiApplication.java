package br.com.senac.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExemploLogbackApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExemploLogbackApiApplication.class, args);
	}

}
