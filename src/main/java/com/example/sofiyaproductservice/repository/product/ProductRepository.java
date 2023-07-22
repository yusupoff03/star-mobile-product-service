package com.example.sofiyaproductservice.repository.product;

import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

}
