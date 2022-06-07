package com.galaxy.registry.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value="company-client", url="http://hackfse2apigateway-env.eba-mpsgx5wq.us-west-2.elasticbeanstalk.com/stock/")
@FeignClient(value="company-client", url="http://localhost:8989/stock/")
public interface RestClient {

	@DeleteMapping("/delete/{companyCode}")
	void deleteStockData(@PathVariable String companyCode);
}
