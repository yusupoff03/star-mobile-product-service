package com.example.sofiyaproductservice.service.product;

import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import com.example.sofiyaproductservice.exception.DataNotFoundException;
import com.example.sofiyaproductservice.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductEntity add(ProductCreatDto product, UUID userId) {
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        productEntity.setUserId(userId);
        return productRepository.save(productEntity);
    }


    public List<ProductEntity> getAllProducts(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).getContent();
    }

    public List<ProductEntity> search(int page, int size, String name) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.searchProductEntitiesByNameContainingIgnoreCase(name, pageable);
    }


    public Boolean deleteById(UUID id, UUID userId) {
        ProductEntity productNotFound = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Product not found"));
        if (productNotFound.getUserId().equals(userId)) {
            productRepository.deleteById(id);
            return true;
        }
        throw new DataNotFoundException("Product not found");
    }


    public ProductEntity update(ProductCreatDto update, UUID productId, UUID userId) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("Product not found"));
        if (productEntity.getUserId().equals(userId)) {
            modelMapper.map(update, productEntity);
            return productRepository.save(productEntity);
        }
        throw new DataNotFoundException("Product not found");
    }
}
