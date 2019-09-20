package com.example.mybatis.db.entity;

/**
 * This POJO represents a car object.
 */
public class Car {

    private Long id_car;

    private String brand;

    private Integer horsepower;

    public Long getId_car() {
        return id_car;
    }

    public void setId_car(Long id_car) {
        this.id_car = id_car;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }
}
