package com.imambux.practice.springbootwebexample.service;

import com.imambux.practice.springbootwebexample.model.City;
import com.imambux.practice.springbootwebexample.repository.CityRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CityService {

  private final CityRepository cityRepository;

  public CityService(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  public City getCity(String code) {
    Optional<City> optionalCity = cityRepository.findById(code);

    if(optionalCity.isPresent()) {
      return optionalCity.get();
    }

    return null;
  }

  public void addCity(City city) {
    cityRepository.save(city);
  }

  public List<City> getAllCities() {
    return cityRepository.findAll();
  }

  public void deleteCity(String code) {
    cityRepository.deleteById(code);
  }

  public void updateCity(City city, String code) {
    if (city.getCode().equals(code)) {
      cityRepository.save(city);
    }
  }
}
