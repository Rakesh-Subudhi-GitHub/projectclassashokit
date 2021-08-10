package com.rk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.entity.UserRegEntity;

public interface UserRepo extends JpaRepository<UserRegEntity, Serializable> {

	public UserRegEntity  findByEmailAndPassword(String email,String password);
	
}
