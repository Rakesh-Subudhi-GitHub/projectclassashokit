package com.rk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MOBILES")
public class Mobile {

	@Id
	@Column(name = "MOBILE_ID")
	private Integer mobile_id;
	
	@Column(name = "BRAND_NAME",length = 20)
	private String brand;
	
	@Column(name = "MOBILE_PRICE",length = 8)
	private Double price;
	
	@Column(name = "MOBILE_RAM",length = 2)
	private Integer ram;
	
	@Column(name = "MOBILE_RATING",length = 2)
	private Integer rating;
	
}//class
