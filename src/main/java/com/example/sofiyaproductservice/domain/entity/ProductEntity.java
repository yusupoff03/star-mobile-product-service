package com.example.sofiyaproductservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    private String model;
    private String name;
    @ManyToOne
    private ProductType productType;

    private Double cost;
}





