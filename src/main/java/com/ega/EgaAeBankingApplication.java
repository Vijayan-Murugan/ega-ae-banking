package com.ega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication()
@EnableAsync
public class EgaAeBankingApplication {
	public static void main(String[] args) {
		SpringApplication.run(EgaAeBankingApplication.class, args);
	}
}
