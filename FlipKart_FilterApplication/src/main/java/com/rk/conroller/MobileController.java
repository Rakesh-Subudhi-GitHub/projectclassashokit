package com.rk.conroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rk.binding.SearchMobile;
import com.rk.entity.Mobile;
import com.rk.service.IMobileService;

@Controller
public class MobileController {

	@Autowired
	private IMobileService mobservice;
	
	//common load form data everytime loadpage or search method need this
	@ModelAttribute
	public void loadDataEveryTime(Model model)
	{
		model.addAttribute("brands",mobservice.getAllBrandNames());
		model.addAttribute("ram",mobservice.getRamSize());
		model.addAttribute("rating",mobservice.getRating());
	}
	
	
	@GetMapping("/loadpage")
	public String loadPage(Model model)
	{
		SearchMobile searchObj=new SearchMobile();
		model.addAttribute("formSearch",searchObj);
		
		return "index";
	}//method
	
	@PostMapping("/SearchMobile") //modelAttribute store the data "formSearch" that take
	public String searchMobile(@ModelAttribute("formSearch") SearchMobile formObj, Model model)
	{
		System.out.println("Search mobile:: "+formObj);
		
		
		//search filters
		List<Mobile> filterMobiles = mobservice.filterMobile(formObj);
		
		model.addAttribute("filterMobilesList",filterMobiles);
		
		return "index";
	}//method
	
	
}//class

