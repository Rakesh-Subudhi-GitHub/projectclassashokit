package com.rk.service;

import org.springframework.stereotype.Service;

import com.rk.customException.CustomeExceptionHandeler;
import com.rk.model.Book;

@Service
public class BookServiceImpl implements IBookService {

	@Override
	public Book findBook(String bookid) {
		
		if(bookid.equals("B100"))
		{
			return new Book("100","java","450");
		}
		else {
				throw new CustomeExceptionHandeler("Book is not found");
		}
		
	}//method

}
