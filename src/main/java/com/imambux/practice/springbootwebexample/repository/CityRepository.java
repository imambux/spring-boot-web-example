package com.imambux.practice.springbootwebexample.repository;

import com.imambux.practice.springbootwebexample.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, String> { }
