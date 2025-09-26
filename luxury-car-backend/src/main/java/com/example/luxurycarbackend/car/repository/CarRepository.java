package com.example.luxurycarbackend.car.repository;

import com.example.luxurycarbackend.car.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findByBrandId(Long brandId);

    @Query("SELECT c FROM CarEntity c WHERE c.brand.name = :brandName")
    List<CarEntity> findByBrandName(@Param("brandName") String brandName);

    List<CarEntity> findByModelContainingIgnoreCase(String model);

    List<CarEntity> findByModelYearBetween(Integer startYear, Integer endYear);

    List<CarEntity> findByHorsepowerGreaterThanEqual(Integer horsepower);

    List<CarEntity> findByPriceBetween(java.math.BigDecimal minPrice, java.math.BigDecimal maxPrice);

    List<CarEntity> findByFuelType(String fuelType);

    List<CarEntity> findByIsLimitedEdition(Boolean isLimitedEdition);

    @Query("SELECT c FROM CarEntity c WHERE " +
           "(:model IS NULL OR LOWER(c.model) LIKE LOWER(CONCAT('%', :model, '%'))) AND " +
           "(:brandId IS NULL OR c.brand.id = :brandId) AND " +
           "(:minYear IS NULL OR c.modelYear >= :minYear) AND " +
           "(:maxYear IS NULL OR c.modelYear <= :maxYear) AND " +
           "(:minPrice IS NULL OR c.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR c.price <= :maxPrice)")
    List<CarEntity> findCarsWithFilters(
        @Param("model") String model,
        @Param("brandId") Long brandId,
        @Param("minYear") Integer minYear,
        @Param("maxYear") Integer maxYear,
        @Param("minPrice") java.math.BigDecimal minPrice,
        @Param("maxPrice") java.math.BigDecimal maxPrice
    );
}