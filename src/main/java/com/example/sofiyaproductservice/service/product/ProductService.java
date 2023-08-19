package com.example.sofiyaproductservice.service.product;

import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductEntity add(ProductCreatDto product,UUID userId,Integer amount,String token);
    List<ProductEntity> getAllProducts(int size, int page);
    List<ProductEntity> search(int page,int size,String name);
    Boolean deleteById(UUID id,UUID userId,String token);
    ProductEntity update(ProductCreatDto update,UUID id,UUID userId);
}
