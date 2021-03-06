package com.galaxy.registry.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompanyRegistry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String companyCode;
	private String companyName;
	private String companyCeo;
	private long companyTurnover;
	private String stockExchange;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCeo() {
		return companyCeo;
	}
	public void setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
	}
	public long getCompanyTurnover() {
		return companyTurnover;
	}
	public void setCompanyTurnover(long companyTurnover) {
		this.companyTurnover = companyTurnover;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	public CompanyRegistry() {
		super();
	}
	public CompanyRegistry(Long id, String companyCode, String companyName, String companyCeo, long companyTurnover,
			String stockExchange) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCeo = companyCeo;
		this.companyTurnover = companyTurnover;
		this.stockExchange = stockExchange;
	}
	
}
