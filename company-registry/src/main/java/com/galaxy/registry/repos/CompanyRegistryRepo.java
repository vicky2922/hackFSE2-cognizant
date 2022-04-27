package com.galaxy.registry.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.registry.model.CompanyRegistry;

@Repository
public interface CompanyRegistryRepo extends JpaRepository<CompanyRegistry, Long>{
	public CompanyRegistry findByCompanyCode(String companyCode);
	Long deleteByCompanyCode(String companyCode);
}
