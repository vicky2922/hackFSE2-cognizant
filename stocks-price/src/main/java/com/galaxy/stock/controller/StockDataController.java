package com.galaxy.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.stock.model.StockData;
import com.galaxy.stock.service.StockDataService;

@RestController
@RequestMapping("/stock")
public class StockDataController {

	@Autowired
	StockDataService stockDataService;
	
	@GetMapping("/greetings")
	public String greetings() {
		return "Welcome to stock-price micro-service";
	}
	
	@PostMapping("/add/{companyCode}/{stockPrice}")
	public StockData saveStockPrice(@PathVariable String companyCode, @PathVariable double stockPrice) {
		return stockDataService.saveStockPrice(companyCode, stockPrice);
	}
	
	@GetMapping("/get/{companyCode}/{startDate}/{endDate}")
	public List<StockData> fetchStockData(@PathVariable String companyCode, @PathVariable String startDate, @PathVariable String endDate){
		return stockDataService.fetchStockData(companyCode, startDate, endDate);
	}
}
