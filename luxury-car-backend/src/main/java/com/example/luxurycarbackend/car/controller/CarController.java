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
@CrossOrigin(origins = "*")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarEntity>> getAllCars() {
        List<CarEntity> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarEntity> getCarById(@PathVariable Long id) {
        return carService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<CarEntity>> getCarsByBrandId(@PathVariable Long brandId) {
        List<CarEntity> cars = carService.getCarsByBrandId(brandId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/brand/name/{brandName}")
    public ResponseEntity<List<CarEntity>> getCarsByBrandName(@PathVariable String brandName) {
        List<CarEntity> cars = carService.getCarsByBrandName(brandName);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CarEntity>> searchCarsByModel(@RequestParam String model) {
        List<CarEntity> cars = carService.searchCarsByModel(model);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/price")
    public ResponseEntity<List<CarEntity>> getCarsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<CarEntity> cars = carService.getCarsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<CarEntity>> getCarsByYear(@PathVariable Integer year) {
        List<CarEntity> cars = carService.getCarsByYear(year);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/fuel/{fuelType}")
    public ResponseEntity<List<CarEntity>> getCarsByFuelType(@PathVariable String fuelType) {
        List<CarEntity> cars = carService.getCarsByFuelType(fuelType);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/body/{bodyType}")
    public ResponseEntity<List<CarEntity>> getCarsByBodyType(@PathVariable String bodyType) {
        List<CarEntity> cars = carService.getCarsByBodyType(bodyType);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/limited")
    public ResponseEntity<List<CarEntity>> getLimitedEditionCars() {
        List<CarEntity> cars = carService.getLimitedEditionCars();
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity<CarEntity> createCar(@RequestBody CarEntity car) {
        CarEntity createdCar = carService.saveCar(car);
        return ResponseEntity.ok(createdCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}