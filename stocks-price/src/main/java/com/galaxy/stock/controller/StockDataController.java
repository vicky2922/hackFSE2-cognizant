package com.galaxy.stock.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.stock.Exception.CompanyNotFoundException;
import com.galaxy.stock.client.RestClient;
import com.galaxy.stock.kafka.KafkaSender;
import com.galaxy.stock.model.StockData;
import com.galaxy.stock.pojo.StockDataWithDetail;
import com.galaxy.stock.service.StockDataService;

@RestController
@RequestMapping("/stock")
public class StockDataController {

	@Autowired
	StockDataService stockDataService;
	
	@Autowired
	RestClient client;
	
	@Autowired
	KafkaSender kafkaSender;
	
	@Value("${restclient.companyregistry.url}")
	String companyRegistryUrl;
	
	@GetMapping("/greetings")
	public String greetings() {
		return "Welcome to stock-price micro-service";
	}
	
	@PostMapping("/add/{companyCode}/{stockPrice}")
	public StockData saveStockPrice(@PathVariable String companyCode, @PathVariable double stockPrice) {
		try {
			URI determinedBasePathUri = URI.create(companyRegistryUrl);
			client.getCompany(determinedBasePathUri, companyCode);
		} catch (Exception e) {
			throw new CompanyNotFoundException();
		}
		StockData stockDataSaved = stockDataService.saveStockPrice(companyCode, stockPrice);
		kafkaSender.send(stockDataSaved.toString());
		return stockDataSaved;
	}
	
	@GetMapping("/get/{companyCode}/{startDate}/{endDate}")
	public List<StockData> fetchStockData(@PathVariable String companyCode, @PathVariable String startDate, @PathVariable String endDate){
		try {
			URI determinedBasePathUri = URI.create(companyRegistryUrl);
			client.getCompany(determinedBasePathUri, companyCode);
		} catch (Exception e) {
			throw new CompanyNotFoundException();
		}
		return stockDataService.fetchStockData(companyCode, startDate, endDate);
	}
	
	@GetMapping("/getWithDetail/{companyCode}/{startDate}/{endDate}")
	public StockDataWithDetail fetchStockDataWithDetail(@PathVariable String companyCode, @PathVariable String startDate, @PathVariable String endDate){
		try {
			URI determinedBasePathUri = URI.create(companyRegistryUrl);
			client.getCompany(determinedBasePathUri, companyCode);
		} catch (Exception e) {
			throw new CompanyNotFoundException();
		}
		StockDataWithDetail stockDataWithDetail = new StockDataWithDetail();
		List<StockData> stockDataList = stockDataService.fetchStockData(companyCode, startDate, endDate);
		stockDataWithDetail.setStockDataList(stockDataList);
		stockDataWithDetail.setAvgOfPrice(stockDataService.getAvgPrice(stockDataList));
		stockDataWithDetail.setMaxPrice(stockDataService.getMaxPrice(stockDataList));
		stockDataWithDetail.setMinPrice(stockDataService.getMinPrice(stockDataList));
		return stockDataWithDetail;
	}
	
	@DeleteMapping("/delete/{companyCode}")
	public void deleteStockData(@PathVariable String companyCode) {
		stockDataService.deleteStockDataByCompanyCode(companyCode);
	}
}
