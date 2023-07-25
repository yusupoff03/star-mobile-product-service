package com.example.sofiyaproductservice.domain.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Tv")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TvEntity extends ProductEntity{


    private Double isSmart;
    private Integer Size;
    private Integer ScreenSpeed;



}
