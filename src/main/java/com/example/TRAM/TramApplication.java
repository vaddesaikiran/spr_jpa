package com.example.TRAM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TramApplication {

	public static void main(String[] args) {
		SpringApplication.run(TramApplication.class, args);
	}

}
