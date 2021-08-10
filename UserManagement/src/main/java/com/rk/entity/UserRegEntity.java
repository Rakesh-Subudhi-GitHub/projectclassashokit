package com.rk.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name="USER_ACCOUNT")
public class UserRegEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "PASSWORD")
	private String password; 
	
	@Column(name = "FIRST_NAME",length = 20)
	private String fname;
	
	@Column(name = "LAST_NAME",length = 20)
	private String lname;
	
	@Column(name = "EMAIL",unique = true)
	private String email;
	
	@Column(name = "PHONE",length = 20)
	private Long phone;
	
	@Column(name = "ACCOUNT_STATUS",length = 10)
	private String AccStatus;
	
	@Column(name = "DOB")
	private LocalDate dob;
	
	@Column(name = "GENDER",length = 10)
	private String gender;
	
	@Column(name = "COUNTRY_ID",length = 5)
	private Integer countryId;
	
	@Column(name = "STATE_ID",length = 5)
	private Integer stateId;
	
	@Column(name = "CITY_ID",length = 5)
	private Integer cityId;
	
	
	
	//Common data
	@Column(name = "CREATED_DATE",updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name = "UPDATED_DATE",insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;
	
}//class
