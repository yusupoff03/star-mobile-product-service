package com.example.sofiyaproductservice.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PhoneDto {

    @NotEmpty(message = "colour cannot be empty")
    private String colour;

    @NotEmpty(message = "size cannot be empty")
    private String size;

    @NotEmpty(message = "weight cannot be empty")
    private double weight;

    @NotEmpty(message = "memory cannot be empty")
    private Integer memory;

    @NotEmpty(message = "Ram cannot be empty")
    private Integer Ram;

    @NotEmpty(message = "battery cannot be empty")
    private double battery;

    @NotEmpty(message = "FrontCamera cannot be empty")
    private Integer FrontCamera;

    @NotEmpty(message = "BackCamera cannot be empty")
    private Integer BackCamera;

    @NotEmpty(message = "description cannot be empty")
    private String description;
}
