package com.galaxy.registry.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.registry.Exception.CompanyCodeAlreadyExistException;
import com.galaxy.registry.Exception.CompanyNotFoundException;
import com.galaxy.registry.Exception.CompanyTurnoverException;
import com.galaxy.registry.client.RestClient;
import com.galaxy.registry.model.CompanyRegistry;
import com.galaxy.registry.service.CompanyRegistryService;

@Transactional
@RestController
@RequestMapping("/company")
public class CompanyRegistryController {
	
	@Autowired
	CompanyRegistryService companyRegistryService;
	
	@Autowired
	RestClient client;
	
	@Value("${restclient.stockprice.url}")
	String stockPriceUrl;
		
	@GetMapping("/greetings")
	public String greetings() {
		return "Welcome to company-registry micro-service";
	}
	
	@PostMapping("/register")
	public String registerCompany(@RequestBody CompanyRegistry companyRegistryRequest) {
		if(companyRegistryRequest.getCompanyTurnover()<10) {
			throw new CompanyTurnoverException();
		}
		CompanyRegistry companyExist = companyRegistryService.getCompanyDetailByCompanyCode(companyRegistryRequest.getCompanyCode());
		if(null!=companyExist) {
			throw new CompanyCodeAlreadyExistException();
		}
		long companyId = companyRegistryService.saveCompany(companyRegistryRequest);
		return "company \""+ companyRegistryRequest.getCompanyName()+"\" got registered with id - "+companyId;
	}
	
	@GetMapping("/info/{companyCode}")
	public CompanyRegistry getCompanyDetail(@PathVariable String companyCode) {
		CompanyRegistry companyRegistry = companyRegistryService.getCompanyDetailByCompanyCode(companyCode);
		if(null == companyRegistry) {
			throw new CompanyNotFoundException();
		}
		return companyRegistry;
	}
	
	@GetMapping("/getall")
	public List<CompanyRegistry> getAllCompanyDetail() {
		return companyRegistryService.getAllCompanyDetail();
	}
	
	@DeleteMapping("/delete/{companyCode}")
	public String deleteCompany(@PathVariable String companyCode) {
		CompanyRegistry companyRegistry = companyRegistryService.getCompanyDetailByCompanyCode(companyCode);
		if(null == companyRegistry) {
			throw new CompanyNotFoundException();
		}
		URI determinedBasePathUri = URI.create(stockPriceUrl);
		client.deleteStockData(determinedBasePathUri,companyCode);
		companyRegistryService.deleteCompany(companyCode);
		return "Company deleted with company code \""+companyCode+"\".";
	}
}
