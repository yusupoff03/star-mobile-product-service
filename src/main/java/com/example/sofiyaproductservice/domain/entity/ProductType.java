package com.example.sofiyaproductservice.domain.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
//        (name = "product_type")
@DiscriminatorColumn(name = "product_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductType extends BaseEntity{

    private String name;
}
