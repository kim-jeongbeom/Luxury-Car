package com.example.luxurycarbackend.car.service;

import com.example.luxurycarbackend.car.dto.CarRequest;
import com.example.luxurycarbackend.car.dto.CarResponse;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {
    List<CarResponse> getAllCars();

    CarResponse getCarById(Long id);

    List<CarResponse> getCarsByBrandId(Long brandId);

    List<CarResponse> getCarsByBrandName(String brandName);

    List<CarResponse> searchCarsByModel(String model);

    List<CarResponse> filterCars(String model, Long brandId, Integer minYear, Integer maxYear,
                                BigDecimal minPrice, BigDecimal maxPrice);

    CarResponse createCar(CarRequest carRequest);

    CarResponse updateCar(Long id, CarRequest carRequest);

    void deleteCar(Long id);
}