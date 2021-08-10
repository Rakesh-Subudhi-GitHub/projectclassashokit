package com.rk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "COUNTRY")
@Data
public class CountriesEntity {

	@	Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COUNTRIES_ID")
	private Integer countryId;
	
	@Column(name = "COUNTRY_CODE",length = 5)
	private String countryCode;
	
	@Column(name = "COUNTRY_NAME")
	private String countryName;
	
}
