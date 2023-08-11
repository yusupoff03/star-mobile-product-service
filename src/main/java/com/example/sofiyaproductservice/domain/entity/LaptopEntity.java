package com.example.sofiyaproductservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "laptop")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LaptopEntity extends BaseEntity {

    private String colour;
    private Double weight;
    private Integer memory;
    private Integer Ram;
    private Integer ScreenSize;
    private Integer Ghz;

    private UUID userId;

    private String model;
    private String name;
    @ManyToOne
    private ProductType productType;

    private Double cost;

}
