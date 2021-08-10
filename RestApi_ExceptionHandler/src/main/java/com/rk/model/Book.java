package com.rk.model;

public class Book {

	private String bookid;
	private String bookname;
	private String bookPrice;
	
	
	//0-param constructor
	public Book() {
	
	}
	
	//all param constructor
	public Book(String bookid, String bookname, String bookPrice) {
		super();
		this.bookid = bookid;
		this.bookname = bookname;
		this.bookPrice = bookPrice;
	}
	
	//setter and getters
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}

	//tostring
	@Override
	public String toString() {
		return "Book [bookid=" + bookid + ", bookname=" + bookname + ", bookPrice=" + bookPrice + "]";
	}
	
	
}
