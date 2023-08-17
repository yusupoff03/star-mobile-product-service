package com.example.sofiyaproductservice.repository.product;

import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> searchProductEntitiesByModelContainingIgnoreCase(String model, Pageable pageable);

//    List<ProductEntity> getUserProduct(int size, int page, UUID userId);
}
