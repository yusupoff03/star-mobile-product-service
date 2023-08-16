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

@Entity(name = "Tv")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TvEntity extends BaseEntity{


    private Double isSmart;
    private Integer Size;
    private Integer ScreenSpeed;

    private UUID userId;

    private String model;
    private String name;
    @ManyToOne
    private ProductType productType;

    private Double cost;


}
