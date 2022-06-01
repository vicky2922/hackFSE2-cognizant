package com.galaxy.stock.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.galaxy.stock.pojo.CompanyData;

@FeignClient(value="company-client", url="http://hackfse2apigateway-env.eba-mpsgx5wq.us-west-2.elasticbeanstalk.com/company/")
public interface RestClient {

	@GetMapping("/info/{companyCode}")
	CompanyData getCompany(@PathVariable String companyCode);
}
