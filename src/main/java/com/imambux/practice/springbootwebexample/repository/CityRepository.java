package com.imambux.practice.springbootwebexample.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.imambux.practice.springbootwebexample.model.Cities;
import com.imambux.practice.springbootwebexample.model.City;
import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepository {

  private final ObjectMapper objectMapper;

  Cities cities;

  public CityRepository(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @PostConstruct
  public void init() throws IOException {
    cities = objectMapper.readValue(new File("cities.json"), Cities.class);
  }

  public Optional<City> getCityByCode(String code) {
    return cities.getCities().stream()
        .filter(city -> city.getCode().equals(code))
        .findFirst();
  }

  public void add(City city) {
    cities.getCities().add(city);

    updateCitiesFile();
  }

  public List<City> getAllCities() {
    return cities.getCities();
  }

  public void deleteByCode(String code) {
    City cityFound = cities.getCities().stream()
        .filter(city -> city.getCode().equals(code))
        .findFirst().get();

    cities.getCities().remove(cityFound);

    updateCitiesFile();
  }

  public void updateCity(City city, String code) {
    Optional<City> optionalCity = cities.getCities().stream()
        .filter(c -> c.getCode().equals(code))
        .findFirst();

    if (!optionalCity.isPresent() || !city.getCode().equals(code)) {
      return;
    }

    City cityFound = optionalCity.get();

    cityFound.setName(city.getName());
    cityFound.setProvince(city.getProvince());

    updateCitiesFile();
  }

  private void updateCitiesFile() {
    try {
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      objectMapper.writeValue(new File("cities.json"), cities);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
