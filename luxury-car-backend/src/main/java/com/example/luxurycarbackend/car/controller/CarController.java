package com.example.luxurycarbackend.car.controller;

import com.example.luxurycarbackend.car.dto.CarRequest;
import com.example.luxurycarbackend.car.dto.CarResponse;
import com.example.luxurycarbackend.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<CarResponse>> getAllCars() {
        List<CarResponse> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        CarResponse car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<CarResponse>> getCarsByBrandId(@PathVariable Long brandId) {
        List<CarResponse> cars = carService.getCarsByBrandId(brandId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/brand/name/{brandName}")
    public ResponseEntity<List<CarResponse>> getCarsByBrandName(@PathVariable String brandName) {
        List<CarResponse> cars = carService.getCarsByBrandName(brandName);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CarResponse>> searchCarsByModel(@RequestParam String model) {
        List<CarResponse> cars = carService.searchCarsByModel(model);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CarResponse>> filterCars(
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice
    ) {
        List<CarResponse> cars = carService.filterCars(model, brandId, minYear, maxYear, minPrice, maxPrice);
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest) {
        CarResponse createdCar = carService.createCar(carRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCar(
            @PathVariable Long id,
            @RequestBody CarRequest carRequest
    ) {
        CarResponse updatedCar = carService.updateCar(id, carRequest);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}