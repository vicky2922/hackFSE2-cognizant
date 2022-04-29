package com.galaxy.stock.repos;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.stock.model.StockData;

@Repository
public interface StockDataRepo extends JpaRepository<StockData, Long> {

	
	@Query(value="SELECT s.* FROM stock_data s WHERE company_code = :companyCode AND DATE(current_date_and_time) BETWEEN :startDate AND :endDate", nativeQuery = true)
	Collection<StockData> findByCodeAndDate(String companyCode, LocalDate startDate, LocalDate endDate);
	
	Collection<StockData> findByCompanyCode(String companyCode);

	@Modifying
	@Transactional
	@Query(value="DELETE FROM stock_data s WHERE company_code = :companyCode", nativeQuery = true)
	void deleteByCompanyCode(String companyCode);
	
}
