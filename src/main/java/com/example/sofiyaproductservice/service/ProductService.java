package com.example.sofiyaproductservice.service;

import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import com.example.sofiyaproductservice.exception.DataNotFoundException;
import com.example.sofiyaproductservice.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService  {


    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductEntity add(ProductCreatDto product) {
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        return productRepository.save(productEntity);
    }


    public List<ProductEntity> getAllProducts(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).getContent();
    }

    public List<ProductEntity> search(int page,int size,String name){
        Sort sort =Sort.by(Sort.Direction.ASC,"name");
        Pageable pageable =  PageRequest.of(page,size,sort);
        return productRepository.searchProductEntitiesByNameContainingIgnoreCase(name,pageable);
    }


    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }


    public ProductEntity update(ProductCreatDto update,UUID id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Product not found"));
        modelMapper.map(update, ProductEntity.class);
        return productRepository.save(productEntity);
    }

}
