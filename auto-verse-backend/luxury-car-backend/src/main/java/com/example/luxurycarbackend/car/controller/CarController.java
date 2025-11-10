package com.example.luxurycarbackend.car.controller;

import com.example.luxurycarbackend.car.entity.CarEntity;
import com.example.luxurycarbackend.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:7777")
public class CarController {

    private final CarService carService;

    // 모든 자동차 조회
    @GetMapping
    public ResponseEntity<List<CarEntity>> getAllCars() {
        List<CarEntity> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    // 자동차 ID로 조회
    @GetMapping("/{id}")
    public ResponseEntity<CarEntity> getCarById(@PathVariable Long id) {
        return carService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 브랜드 ID로 자동차 목록 조회
    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<CarEntity>> getCarsByBrandId(@PathVariable Long brandId) {
        List<CarEntity> cars = carService.getCarsByBrandId(brandId);
        return ResponseEntity.ok(cars);
    }

    // 브랜드 이름으로 자동차 목록 조회
    @GetMapping("/brand/name/{brandName}")
    public ResponseEntity<List<CarEntity>> getCarsByBrandName(@PathVariable String brandName) {
        List<CarEntity> cars = carService.getCarsByBrandName(brandName);
        return ResponseEntity.ok(cars);
    }

    // 모델명으로 검색
    @GetMapping("/search")
    public ResponseEntity<List<CarEntity>> searchCarsByModel(@RequestParam String model) {
        List<CarEntity> cars = carService.searchCarsByModel(model);
        return ResponseEntity.ok(cars);
    }

    // 가격 범위로 검색
    @GetMapping("/price")
    public ResponseEntity<List<CarEntity>> getCarsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<CarEntity> cars = carService.getCarsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(cars);
    }

    // 연도별 검색
    @GetMapping("/year/{year}")
    public ResponseEntity<List<CarEntity>> getCarsByYear(@PathVariable Integer year) {
        List<CarEntity> cars = carService.getCarsByYear(year);
        return ResponseEntity.ok(cars);
    }

    // 연료 타입별 검색
    @GetMapping("/fuel/{fuelType}")
    public ResponseEntity<List<CarEntity>> getCarsByFuelType(@PathVariable String fuelType) {
        List<CarEntity> cars = carService.getCarsByFuelType(fuelType);
        return ResponseEntity.ok(cars);
    }

    // 차체 타입별 검색
    @GetMapping("/body/{bodyType}")
    public ResponseEntity<List<CarEntity>> getCarsByBodyType(@PathVariable String bodyType) {
        List<CarEntity> cars = carService.getCarsByBodyType(bodyType);
        return ResponseEntity.ok(cars);
    }

    // 한정판 자동차 조회
    @GetMapping("/limited")
    public ResponseEntity<List<CarEntity>> getLimitedEditionCars() {
        List<CarEntity> cars = carService.getLimitedEditionCars();
        return ResponseEntity.ok(cars);
    }
}
