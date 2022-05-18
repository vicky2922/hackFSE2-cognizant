package com.galaxy.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class StocksPriceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocksPriceApplication.class, args);
	}

}
