package com.imambux.practice.springbootwebexample.controller;

import com.imambux.practice.springbootwebexample.model.City;
import com.imambux.practice.springbootwebexample.service.CityService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

  private final CityService cityService;

  public CityController(CityService cityService) {
    this.cityService = cityService;
  }

  @GetMapping("/city")
  public ResponseEntity<City> getCity(String code) {
    City city = cityService.getCity(code);

    if(city != null) {
      return ResponseEntity.ok(city);
    }

    return new ResponseEntity<>(new City(), HttpStatus.NOT_FOUND);
  }

  @PostMapping("/city")
  public void addCity(@RequestBody City city) {
    cityService.addCity(city);
  }

  @GetMapping("/cities")
  public List<City> getAllCities() {
    return cityService.getAllCities();
  }

  @DeleteMapping("/city")
  public void deleteCity(String code) {
    cityService.deleteCity(code);
  }

  @PutMapping("/city/{code}")
  public void updateCity(@RequestBody City city, @PathVariable String code) {
    cityService.updateCity(city, code);
  }

}
