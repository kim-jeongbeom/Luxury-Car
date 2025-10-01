package com.example.luxurycarbackend.car.entity;

import com.example.luxurycarbackend.brand.entity.BrandEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(name = "model_year")
    private Integer modelYear;

    private String engine;

    private Integer horsepower;

    @Column(name = "max_speed")
    private Integer maxSpeed;

    @Column(name = "zero_to_hundred", precision = 3, scale = 1)
    private BigDecimal zeroToHundred;

    private BigDecimal price;

    @Column(name = "price_currency")
    private String priceCurrency;

    private String transmission;

    @Column(name = "drive_type")
    private String driveType;

    @Column(name = "fuel_type")
    private String fuelType;

    private String bodyType;

    private Integer doors;

    private Integer seats;

    private String color;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_limited_edition")
    private Boolean isLimitedEdition;

    @Column(name = "production_count")
    private Integer productionCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private BrandEntity brand;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}