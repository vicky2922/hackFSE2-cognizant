package com.galaxy.stock.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.galaxy.stock.pojo.CompanyData;

@FeignClient(value="company-client", url="http://localhost:8989/company/")
public interface RestClient {

	@GetMapping("/info/{companyCode}")
	CompanyData getCompany(@PathVariable String companyCode);
}