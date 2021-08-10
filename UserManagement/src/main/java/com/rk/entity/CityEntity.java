package com.rk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CITY")
@Data
public class CityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CITY_ID")
	private Integer cityid;
	
	@Column(name = "CITY_NAME")
	private String cityName;
	
	@Column(name = "STATE_ID")
	private Integer stateId;
	
}
