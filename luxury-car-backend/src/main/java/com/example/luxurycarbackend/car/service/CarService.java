package com.example.luxurycarbackend.car.service;

import com.example.luxurycarbackend.car.entity.CarEntity;
import com.example.luxurycarbackend.car.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    // 모든 자동차 조회
    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }

    // 자동차 ID로 조회
    public Optional<CarEntity> getCarById(Long id) {
        return carRepository.findById(id);
    }

    // 브랜드 ID로 자동차 목록 조회
    public List<CarEntity> getCarsByBrandId(Long brandId) {
        return carRepository.findByBrandId(brandId);
    }

    // 브랜드 이름으로 자동차 목록 조회
    public List<CarEntity> getCarsByBrandName(String brandName) {
        return carRepository.findByBrandName(brandName);
    }

    // 모델명으로 검색
    public List<CarEntity> searchCarsByModel(String model) {
        return carRepository.findByModelContainingIgnoreCase(model);
    }

    // 가격 범위로 검색
    public List<CarEntity> getCarsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return carRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // 연도별 검색
    public List<CarEntity> getCarsByYear(Integer year) {
        return carRepository.findByModelYear(year);
    }

    // 연료 타입별 검색
    public List<CarEntity> getCarsByFuelType(String fuelType) {
        return carRepository.findByFuelType(fuelType);
    }

    // 차체 타입별 검색
    public List<CarEntity> getCarsByBodyType(String bodyType) {
        return carRepository.findByBodyType(bodyType);
    }

    // 한정판 자동차 조회
    public List<CarEntity> getLimitedEditionCars() {
        return carRepository.findByIsLimitedEdition(true);
    }

    // 자동차 저장
    public CarEntity saveCar(CarEntity car) {
        return carRepository.save(car);
    }

    // 자동차 삭제
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}