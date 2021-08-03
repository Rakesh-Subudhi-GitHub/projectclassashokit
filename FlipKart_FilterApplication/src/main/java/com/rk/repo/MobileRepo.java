package com.rk.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rk.entity.Mobile;

public interface MobileRepo extends JpaRepository<Mobile, Serializable>{

	@Query("select distinct(brand) from Mobile") //unique brand name only collect
	public List<String> findByBrandNames();
	

	@Query("select distinct(ram) from Mobile") //unique Ram size collect
	public List<String> findRamSize();
	

	@Query("select distinct(price) from Mobile") //unique price  only collect
	public List<String> findPriceRange();
	

	@Query("select distinct(rating) from Mobile") //unique rating  only collect
	public List<String> findRating();
	

}
