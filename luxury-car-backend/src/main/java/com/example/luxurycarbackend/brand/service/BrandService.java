package com.example.luxurycarbackend.brand.service;

import com.example.luxurycarbackend.brand.entity.BrandEntity;
import java.util.List;

public interface BrandService {
    // CRUD 기본 메서드
    List<BrandEntity> findAll();           // 전체 조회
    BrandEntity findById(Long id);         // 단건 조회
    BrandEntity save(BrandEntity brand);   // 등록/수정
    void deleteById(Long id);              // 삭제
    
    // 추가 조회 메서드
    BrandEntity findByName(String name);
    List<BrandEntity> findByCountry(String country);
}
