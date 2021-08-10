package com.rk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.entity.CountriesEntity;

public interface CountriesRepo extends JpaRepository<CountriesEntity, Serializable> {

}
