package com.example.luxurycarbackend.brand.service;

import com.example.luxurycarbackend.brand.entity.BrandEntity;
import com.example.luxurycarbackend.brand.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public List<BrandEntity> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public BrandEntity findById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
    }

    @Override
    public BrandEntity save(BrandEntity brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void deleteById(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new RuntimeException("Brand not found with id: " + id);
        }
        brandRepository.deleteById(id);
    }

    @Override
    public BrandEntity findByName(String name) {
        return brandRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Brand not found with name: " + name));
    }

    @Override
    public List<BrandEntity> findByCountry(String country) {
        return brandRepository.findByCountry(country);
    }
}
