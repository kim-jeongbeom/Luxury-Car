package com.example.luxurycarbackend.car.service;

import com.example.luxurycarbackend.brand.entity.BrandEntity;
import com.example.luxurycarbackend.brand.repository.BrandRepository;
import com.example.luxurycarbackend.car.dto.CarRequest;
import com.example.luxurycarbackend.car.dto.CarResponse;
import com.example.luxurycarbackend.car.entity.CarEntity;
import com.example.luxurycarbackend.car.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final BrandRepository brandRepository;

    @Override
    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CarResponse getCarById(Long id) {
        CarEntity car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
        return convertToResponse(car);
    }

    @Override
    public List<CarResponse> getCarsByBrandId(Long brandId) {
        return carRepository.findByBrandId(brandId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarResponse> getCarsByBrandName(String brandName) {
        return carRepository.findByBrandName(brandName).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarResponse> searchCarsByModel(String model) {
        return carRepository.findByModelContainingIgnoreCase(model).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarResponse> filterCars(String model, Long brandId, Integer minYear, Integer maxYear,
                                       BigDecimal minPrice, BigDecimal maxPrice) {
        return carRepository.findCarsWithFilters(model, brandId, minYear, maxYear, minPrice, maxPrice).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CarResponse createCar(CarRequest carRequest) {
        BrandEntity brand = brandRepository.findById(carRequest.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + carRequest.getBrandId()));

        CarEntity car = CarEntity.builder()
                .model(carRequest.getModel())
                .modelYear(carRequest.getModelYear())
                .engine(carRequest.getEngine())
                .horsepower(carRequest.getHorsepower())
                .maxSpeed(carRequest.getMaxSpeed())
                .zeroToHundred(carRequest.getZeroToHundred())
                .price(carRequest.getPrice())
                .priceCurrency(carRequest.getPriceCurrency())
                .transmission(carRequest.getTransmission())
                .driveType(carRequest.getDriveType())
                .fuelType(carRequest.getFuelType())
                .bodyType(carRequest.getBodyType())
                .doors(carRequest.getDoors())
                .seats(carRequest.getSeats())
                .color(carRequest.getColor())
                .description(carRequest.getDescription())
                .imageUrl(carRequest.getImageUrl())
                .isLimitedEdition(carRequest.getIsLimitedEdition())
                .productionCount(carRequest.getProductionCount())
                .brand(brand)
                .build();

        CarEntity savedCar = carRepository.save(car);
        return convertToResponse(savedCar);
    }

    @Override
    @Transactional
    public CarResponse updateCar(Long id, CarRequest carRequest) {
        CarEntity existingCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));

        BrandEntity brand = brandRepository.findById(carRequest.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + carRequest.getBrandId()));

        existingCar.setModel(carRequest.getModel());
        existingCar.setModelYear(carRequest.getModelYear());
        existingCar.setEngine(carRequest.getEngine());
        existingCar.setHorsepower(carRequest.getHorsepower());
        existingCar.setMaxSpeed(carRequest.getMaxSpeed());
        existingCar.setZeroToHundred(carRequest.getZeroToHundred());
        existingCar.setPrice(carRequest.getPrice());
        existingCar.setPriceCurrency(carRequest.getPriceCurrency());
        existingCar.setTransmission(carRequest.getTransmission());
        existingCar.setDriveType(carRequest.getDriveType());
        existingCar.setFuelType(carRequest.getFuelType());
        existingCar.setBodyType(carRequest.getBodyType());
        existingCar.setDoors(carRequest.getDoors());
        existingCar.setSeats(carRequest.getSeats());
        existingCar.setColor(carRequest.getColor());
        existingCar.setDescription(carRequest.getDescription());
        existingCar.setImageUrl(carRequest.getImageUrl());
        existingCar.setIsLimitedEdition(carRequest.getIsLimitedEdition());
        existingCar.setProductionCount(carRequest.getProductionCount());
        existingCar.setBrand(brand);

        CarEntity updatedCar = carRepository.save(existingCar);
        return convertToResponse(updatedCar);
    }

    @Override
    @Transactional
    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new RuntimeException("Car not found with id: " + id);
        }
        carRepository.deleteById(id);
    }

    private CarResponse convertToResponse(CarEntity car) {
        return CarResponse.builder()
                .id(car.getId())
                .model(car.getModel())
                .modelYear(car.getModelYear())
                .engine(car.getEngine())
                .horsepower(car.getHorsepower())
                .maxSpeed(car.getMaxSpeed())
                .zeroToHundred(car.getZeroToHundred())
                .price(car.getPrice())
                .priceCurrency(car.getPriceCurrency())
                .transmission(car.getTransmission())
                .driveType(car.getDriveType())
                .fuelType(car.getFuelType())
                .bodyType(car.getBodyType())
                .doors(car.getDoors())
                .seats(car.getSeats())
                .color(car.getColor())
                .description(car.getDescription())
                .imageUrl(car.getImageUrl())
                .isLimitedEdition(car.getIsLimitedEdition())
                .productionCount(car.getProductionCount())
                .brandId(car.getBrand().getId())
                .brandName(car.getBrand().getName())
                .brandNameKr(car.getBrand().getNameKr())
                .createdAt(car.getCreatedAt())
                .updatedAt(car.getUpdatedAt())
                .build();
    }
}