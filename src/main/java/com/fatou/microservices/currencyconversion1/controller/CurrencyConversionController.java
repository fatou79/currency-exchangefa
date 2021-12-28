package com.fatou.microservices.currencyconversion1.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fatou.microservices.currencyconversion1.bean.CurrencyConversion;
import com.fatou.microservices.currencyconversion1.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
		
		HashMap<String,String>  uriVariables = new HashMap<>();
		uriVariables.put("from",from);
		uriVariables.put("to",to);

		
		
		ResponseEntity<CurrencyConversion> ResponseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				 CurrencyConversion.class , uriVariables );
	CurrencyConversion diagne =	ResponseEntity.getBody();
		
	return new CurrencyConversion(diagne.getId(),from,to,quantity,diagne.getConversionMultiple(),quantity.multiply(diagne.getConversionMultiple()),
		diagne.getEnvironment());
	
		
} 
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionfeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
		
		CurrencyConversion diagne =	proxy.retrieveExchange(from, to);
		return new CurrencyConversion(diagne.getId(),from,to,quantity,diagne.getConversionMultiple(),quantity.multiply(diagne.getConversionMultiple()),
			diagne.getEnvironment() + " " + "Feign"  );
		
			
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}