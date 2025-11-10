package com.example.autoversebackend.car.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
    private Long id;
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
    private String brandName;
    private String brandNameKr;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}