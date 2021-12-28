package com.fatou.microservices.currencyconversion1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyConversion1Application {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversion1Application.class, args);
	}

}
