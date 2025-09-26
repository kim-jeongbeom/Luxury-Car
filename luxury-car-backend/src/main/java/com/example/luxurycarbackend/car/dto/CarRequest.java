package com.example.luxurycarbackend.car.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarRequest {
    private String model;
    private Integer modelYear;
    private String engine;
    private Integer horsepower;
    private Integer maxSpeed;
    private BigDecimal zeroToHundred;
    private BigDecimal price;
    private String priceCurrency;
    private String transmission;
    private String driveType;
    private String fuelType;
    private String bodyType;
    private Integer doors;
    private Integer seats;
    private String color;
    private String description;
    private String imageUrl;
    private Boolean isLimitedEdition;
    private Integer productionCount;
    private Long brandId;
}