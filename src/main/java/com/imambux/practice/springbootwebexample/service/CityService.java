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
    Optional<City> optionalCity = cityRepository.getCityByCode(code);

    if(optionalCity.isPresent()) {
      return optionalCity.get();
    }

    return null;
  }

  public void addCity(City city) {
    cityRepository.add(city);
  }

  public List<City> getAllCities() {
    return cityRepository.getAllCities();
  }

  public void deleteCity(String code) {
    cityRepository.deleteByCode(code);
  }

  public void updateCity(City city, String code) {
    cityRepository.updateCity(city, code);
  }
}
