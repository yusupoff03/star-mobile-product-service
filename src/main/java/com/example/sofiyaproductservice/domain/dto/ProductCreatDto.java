package com.example.sofiyaproductservice.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductCreatDto {

    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotEmpty(message = "model cannot be empty")
    private String model;

    @NotEmpty(message = "cost cannot be empty")
    private Double cost;

    private String productType;
}
