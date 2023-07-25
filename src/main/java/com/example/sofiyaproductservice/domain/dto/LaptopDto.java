package com.example.sofiyaproductservice.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LaptopDto {

    @NotEmpty(message = "colour cannot be empty")
    private String colour;

    @NotEmpty(message = "weight cannot be empty")
    private double weight;

    @NotEmpty(message = "memory cannot be empty")
    private Integer memory;

    @NotEmpty(message = "Ram cannot be empty")
    private Integer Ram;

    @NotEmpty(message = "ScreenSize cannot be empty")
    private Integer ScreenSize;

    @NotEmpty(message = "Ghz cannot be empty")
    private Integer Ghz;
}
