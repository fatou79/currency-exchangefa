package com.fatou.microservices.currencyconversion1.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fatou.microservices.currencyconversion1.bean.CurrencyConversion;


@FeignClient(name = "currency-exchange1", url= "localhost:8000")
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchange(@PathVariable String from, @PathVariable String to) ;
	
}
