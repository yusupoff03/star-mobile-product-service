package com.example.sofiyaproductservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;


@Entity(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductEntity extends BaseEntity{
    private String productType;
}





