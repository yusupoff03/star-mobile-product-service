package com.example.sofiyaproductservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;

@Entity(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductEntity extends BaseEntity{
    private UUID userId;
    private String name;

    private String model;

    @ManyToOne
    private ProductType type;

    private Double cost;
}
