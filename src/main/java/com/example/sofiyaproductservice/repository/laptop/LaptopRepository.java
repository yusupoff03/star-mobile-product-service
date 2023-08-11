package com.example.sofiyaproductservice.repository.laptop;

import com.example.sofiyaproductservice.domain.entity.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LaptopRepository extends JpaRepository<LaptopEntity, UUID> {

}
