package com.example.autoversebackend.brand.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "name_kr")
    private String nameKr;
    
    private int founded;
    
    private String country;
    
    private String category;
    
    private String description;
    
    private String image;
    
    private String representative;
}
