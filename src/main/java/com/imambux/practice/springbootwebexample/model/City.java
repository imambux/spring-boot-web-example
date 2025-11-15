package com.imambux.practice.springbootwebexample.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class City {

  @Id
  private String code;
  private String name;
  private String province;

}
