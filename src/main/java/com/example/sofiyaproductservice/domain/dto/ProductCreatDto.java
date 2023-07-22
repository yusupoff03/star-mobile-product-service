package com.example.sofiyaproductservice.domain.dto;

import com.example.sofiyaproductservice.domain.entity.ProductType;
import lombok.*;

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
