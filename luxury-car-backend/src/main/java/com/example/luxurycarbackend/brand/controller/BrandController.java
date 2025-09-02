package com.example.luxurycarbackend.brand.controller;

import com.example.luxurycarbackend.brand.entity.BrandEntity;
import com.example.luxurycarbackend.brand.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BrandController {
    
    private final BrandService brandService;
    
    // 전체 조회 - GET /api/brands
    @GetMapping
    public ResponseEntity<List<BrandEntity>> getAllBrands() {
        List<BrandEntity> brands = brandService.findAll();
        return ResponseEntity.ok(brands);
    }
    
    // 단건 조회 - GET /api/brands/1
    @GetMapping("/{id}")
    public ResponseEntity<BrandEntity> getBrandById(@PathVariable Long id) {
        BrandEntity brand = brandService.findById(id);
        return ResponseEntity.ok(brand);
    }
    
    // 등록 - POST /api/brands
    @PostMapping
    public ResponseEntity<BrandEntity> createBrand(@RequestBody BrandEntity brand) {
        BrandEntity savedBrand = brandService.save(brand);
        return ResponseEntity.ok(savedBrand);
    }
    
    // 수정 - PUT /api/brands/1
    @PutMapping("/{id}")
    public ResponseEntity<BrandEntity> updateBrand(@PathVariable Long id, @RequestBody BrandEntity brand) {
        brand.setId(id); // ID 설정
        BrandEntity updatedBrand = brandService.save(brand);
        return ResponseEntity.ok(updatedBrand);
    }
    
    // 삭제 - DELETE /api/brands/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    // 이름으로 조회 - GET /api/brands/name/BMW
    @GetMapping("/name/{name}")
    public ResponseEntity<BrandEntity> getBrandByName(@PathVariable String name) {
        BrandEntity brand = brandService.findByName(name);
        return ResponseEntity.ok(brand);
    }
    
    // 국가별 조회 - GET /api/brands/country/Germany
    @GetMapping("/country/{country}")
    public ResponseEntity<List<BrandEntity>> getBrandsByCountry(@PathVariable String country) {
        List<BrandEntity> brands = brandService.findByCountry(country);
        return ResponseEntity.ok(brands);
    }
}
