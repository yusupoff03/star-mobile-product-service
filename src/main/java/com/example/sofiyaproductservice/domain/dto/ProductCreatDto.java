package com.example.sofiyaproductservice.domain.dto;

import com.example.sofiyaproductservice.domain.entity.ProductType;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductCreatDto {
    private String name;
    private String model;
    private ProductType productType;
    private Double cost;
}
