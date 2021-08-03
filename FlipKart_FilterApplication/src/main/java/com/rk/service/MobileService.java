package com.rk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.rk.binding.SearchMobile;
import com.rk.entity.Mobile;
import com.rk.repo.MobileRepo;

@Service
public class MobileService implements IMobileService{

	    @Autowired
		private MobileRepo mobRepo;
	    
	    @Override
	    public List<Mobile> getAllMobile() {
	    	
	    	return mobRepo.findAll();
	    }
	    
	    @Override
	    public List<String> getAllBrandNames() {
	    
	    	return mobRepo.findByBrandNames();
	    }
	    
	    @Override
	    public List<String> getRamSize() {
	    return mobRepo.findRamSize();
	    }
	    
	    @Override
	    public List<String> getPriceRange() {
	    return mobRepo.findPriceRange();
	    }
	    
	    @Override
	    public List<String> getRating() {
	    return mobRepo.findRating();
	    }
	    
	    //filter the mobile based on user 
	    @Override
	    public List<Mobile> filterMobile(SearchMobile formObj) {
	    
	    	Mobile entity=new Mobile();
	    	
	    	//BeanUtils.copyProperties(formObj, entity);
	    	//not set price so manually set value
	    	
	    	if(null!= formObj.getBrand() && !"".equals(formObj.getBrand()))
	    	{
	    		entity.setBrand(formObj.getBrand());	
	    	}
	    	
	    	entity.setRam(formObj.getRam());
	    	entity.setRating(formObj.getRating());
	    	
	    	
	    	//based on user desided filters that search
	    	Example<Mobile> example=Example.of(entity);  //simple put it and search it
	    	
	    	List<Mobile> findAllMobileList = mobRepo.findAll(example);
	    	
	    	//based on price to take filters  
	    	//but it take price then go it other wise return alreadyfilterlist
	    	if(formObj.getPrice()!=null && !"".equals(formObj.getPrice()))
	    	{
	    		List<Mobile> filterMobileByPrice = filterMobileByPrice(findAllMobileList,formObj.getPrice());
		    	return filterMobileByPrice;
	    	}
	    	else {
	    	return findAllMobileList;
	    	}
	    	
	    }

		private List<Mobile> filterMobileByPrice(List<Mobile> mobiles, Double price) {
			
			List<Mobile> filterBasedOnPrice= new ArrayList<>();
			mobiles.forEach(mobile -> {
				if(mobile.getPrice() < price)
				{
					filterBasedOnPrice.add(mobile);
				}
			});
			
			//finally return
			return filterBasedOnPrice;
		}
	    
}//class
