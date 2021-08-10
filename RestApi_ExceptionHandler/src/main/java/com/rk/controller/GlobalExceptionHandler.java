package com.rk.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rk.customException.ApiError;
import com.rk.customException.CustomeExceptionHandeler;



//all controller come any kind of exception that should handel here

@RestController
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = NullPointerException.class)
	public String hanlingExpeption(Model model)
	{
		model.addAttribute("msg","EXCEPTION IS CAN COME IT HANDEL IT");
		return "error";
	}
	
	//custom Exception
//	@ExceptionHandler(value = CustomeExceptionHandeler.class)
//	public String noBookFoundException(Model model)
//	{
//		model.addAttribute("nobook","No Book Found Here....");
//		return "cutomeErr";
//	}
	
	@ExceptionHandler(value = CustomeExceptionHandeler.class)
	public ResponseEntity<ApiError> noBookFoundException()
	{
		ApiError error=new ApiError(400, "No Book Found", new Date());
		
		return new ResponseEntity<ApiError>(error,HttpStatus.NOT_FOUND);
	}
	
	
}
