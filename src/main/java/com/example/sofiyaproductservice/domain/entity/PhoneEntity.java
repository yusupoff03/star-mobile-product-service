package com.example.sofiyaproductservice.domain.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "phone")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhoneEntity extends ProductEntity {
    private String colour;
    private String size;
    private double weight;
    private Integer memory;
    private Integer Ram;
    private double battery;
    private Integer FrontCamera;
    private Integer BackCamera;
    private String description;
}
