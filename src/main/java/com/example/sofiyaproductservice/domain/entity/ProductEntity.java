package com.example.sofiyaproductservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductEntity extends BaseEntity{
    private String name;
    private String model;
    @ManyToOne
    private ProductType type;
    private Double cost;
}
