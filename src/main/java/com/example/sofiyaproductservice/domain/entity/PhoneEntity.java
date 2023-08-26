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

@Entity(name = "phone")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhoneEntity extends BaseEntity {
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
