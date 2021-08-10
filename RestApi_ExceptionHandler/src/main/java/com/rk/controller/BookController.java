package com.rk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rk.model.Book;
import com.rk.service.IBookService;

@RestController
public class BookController {

	@Autowired
	private IBookService bookService;
	
	@GetMapping(value="/book",produces = {"application/json"} )
	public Book findBook(@RequestParam("bookid") String bookid) {
	 
		return bookService.findBook(bookid);
	}
	
}
