package com.galaxy.stock.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxy.stock.model.StockData;
import com.galaxy.stock.repos.StockDataRepo;

@Service
public class StockDataService {
	
	@Autowired
	StockDataRepo stockDataRepo;

	public StockData saveStockPrice(String companyCode, double stockPrice) {
		StockData stockData = new StockData();
		stockData.setCompanyCode(companyCode);
		stockData.setStockPrice(stockPrice);
		return stockDataRepo.save(stockData);
	}

	public List<StockData> fetchStockData(String companyCode, String startDate, String endDate) {
		List<StockData> returnList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDateObj = LocalDate.parse(startDate, formatter);
		LocalDate endDateObj = LocalDate.parse(endDate, formatter);
		Collection<StockData> stockData = stockDataRepo.findByCodeAndDate(companyCode, startDateObj, endDateObj);
		returnList.addAll(stockData);
		return returnList;
	}

	public void deleteStockDataByCompanyCode(String companyCode) {
		stockDataRepo.deleteByCompanyCode(companyCode);
	}
}
