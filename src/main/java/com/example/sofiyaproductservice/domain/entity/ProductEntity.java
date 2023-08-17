package com.example.sofiyaproductservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;


@Entity
//        (name = "product")
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductEntity extends BaseEntity{
    private UUID userId;

    private String model;
    private String name;
    private String productType;

    private Double cost;
}





