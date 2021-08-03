package com.rk.service;

import java.util.List;

import com.rk.binding.SearchMobile;
import com.rk.entity.Mobile;

public interface IMobileService {

	public List<Mobile> getAllMobile();
	
	public List<String> getAllBrandNames();
	
	public List<String> getRamSize();
	
	public List<String> getPriceRange();
	
	public List<String> getRating();
	
	public List<Mobile> filterMobile(SearchMobile formObj);
}
