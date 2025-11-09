package com.imambux.practice.springbootwebexample.repository;

import com.imambux.practice.springbootwebexample.model.City;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepository {

  List<City> cities = new ArrayList<>();

  @PostConstruct
  public void init() {
    cities.add(new City("hyd", "Hyderabad", "Sindh"));
    cities.add(new City("khi", "Karachi", "Sindh"));
    cities.add(new City("lhr", "Lahore", "Punjab"));
    cities.add(new City("psh", "Peshawar", "KPK"));
  }

  public Optional<City> getCityByCode(String code) {
    return cities.stream()
        .filter(city -> city.getCode().equals(code))
        .findFirst();
  }

  public void add(City city) {
    cities.add(city);
  }

  public List<City> getAllCities() {
    return cities;
  }

  public void deleteByCode(String code) {
    City cityFound = cities.stream()
        .filter(city -> city.getCode().equals(code))
        .findFirst().get();

    cities.remove(cityFound);
  }

  public void updateCity(City city, String code) {
    Optional<City> optionalCity = cities.stream()
        .filter(c -> c.getCode().equals(code))
        .findFirst();

    if (!optionalCity.isPresent() || !city.getCode().equals(code)) {
      return;
    }

    City cityFound = optionalCity.get();

    cityFound.setName(city.getName());
    cityFound.setProvince(city.getProvince());
  }

}
