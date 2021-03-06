package com.galaxy.registry.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxy.registry.Exception.CompanyCodeAlreadyExistException;
import com.galaxy.registry.model.CompanyRegistry;
import com.galaxy.registry.repos.CompanyRegistryRepo;

@Transactional
@Service
public class CompanyRegistryService {
	
	@Autowired
	private CompanyRegistryRepo companyRegistryRepo;

	public Long saveCompany(CompanyRegistry companyRegistryRequest) throws CompanyCodeAlreadyExistException {
		
		return companyRegistryRepo.save(companyRegistryRequest).getId();
	}

	public CompanyRegistry getCompanyDetailByCompanyCode(String companyCode) {
		return companyRegistryRepo.findByCompanyCode(companyCode);
	}

	public List<CompanyRegistry> getAllCompanyDetail() {
		return companyRegistryRepo.findAll();
	}

	public void deleteCompany(String companyCode) {
		companyRegistryRepo.deleteByCompanyCode(companyCode);
	}
}
