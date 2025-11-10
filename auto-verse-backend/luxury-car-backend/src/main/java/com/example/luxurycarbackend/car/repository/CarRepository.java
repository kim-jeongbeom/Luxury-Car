package com.example.luxurycarbackend.car.repository;

import com.example.luxurycarbackend.car.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    // 브랜드 ID로 자동차 목록 조회
    List<CarEntity> findByBrandId(Long brandId);

    // 브랜드 이름으로 자동차 목록 조회
    List<CarEntity> findByBrandName(String brandName);

    // 모델명으로 검색
    List<CarEntity> findByModelContainingIgnoreCase(String model);

    // 가격 범위로 검색
    List<CarEntity> findByPriceBetween(java.math.BigDecimal minPrice, java.math.BigDecimal maxPrice);

    // 연도별 검색
    List<CarEntity> findByModelYear(Integer year);

    // 연료 타입별 검색
    List<CarEntity> findByFuelType(String fuelType);

    // 차체 타입별 검색
    List<CarEntity> findByBodyType(String bodyType);

    // 한정판 여부로 검색
    List<CarEntity> findByIsLimitedEdition(Boolean isLimitedEdition);
}
