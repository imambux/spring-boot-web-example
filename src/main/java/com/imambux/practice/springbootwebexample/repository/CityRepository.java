package com.imambux.practice.springbootwebexample.repository;

import com.imambux.practice.springbootwebexample.model.City;
import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepository {

  List<City> cities = new ArrayList<>();

  @PostConstruct
  public void init() {
    try(BufferedReader br = new BufferedReader(new FileReader("cities.csv"))) {

      br.lines().forEach(line -> {
        String[] fields = line.split(",");
        City city = new City(fields[0], fields[1], fields[2]);

        cities.add(city);
      });
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<City> getCityByCode(String code) {
    return cities.stream()
        .filter(city -> city.getCode().equals(code))
        .findFirst();
  }

  public void add(City city) {
    try(BufferedWriter bw = new BufferedWriter(new FileWriter("cities.csv", true))) {
      String newCity = "\n" + city.getCode() + "," + city.getName() + "," + city.getProvince();
      bw.write(newCity);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

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
