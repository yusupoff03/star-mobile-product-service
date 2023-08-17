package com.example.sofiyaproductservice.repository.laptop;

import com.example.sofiyaproductservice.domain.entity.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, UUID> {

}
