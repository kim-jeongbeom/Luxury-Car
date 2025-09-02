package com.example.luxurycarbackend.brand.repository;

import com.example.luxurycarbackend.brand.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    // 기본 CRUD는 JpaRepository에서 제공:
    // save() - 저장/수정
    // findById() - ID로 조회
    // findAll() - 전체 조회
    // deleteById() - 삭제
    
    // 추가 커스텀 조회 메서드
    Optional<BrandEntity> findByName(String name);
    List<BrandEntity> findByCountry(String country);
    List<BrandEntity> findByCategory(String category);
}
