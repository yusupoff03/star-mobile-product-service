package com.example.sofiyaproductservice.service.product;

import com.example.sofiyaproductservice.domain.dto.InventoryDto;
import com.example.sofiyaproductservice.domain.dto.ProductCreatDto;
import com.example.sofiyaproductservice.domain.entity.ProductEntity;
import com.example.sofiyaproductservice.exception.DataNotFoundException;
import com.example.sofiyaproductservice.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    @Value("${services.inventory-url}")
    private String inventoryServiceUrl;

    public ProductEntity add(ProductCreatDto product, UUID userId,Integer amount,String token) {
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        productEntity.setUserId(userId);
        ProductEntity save = productRepository.save(productEntity);
        addInventory(save,amount,token);
        return save;
    }


    public List<ProductEntity> getAllProducts(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProductEntity> content = productRepository.findAll(pageable).getContent();
        if(content.isEmpty()){
            throw new DataNotFoundException("Products not found");
        }
        return content;
    }

    public List<ProductEntity> search(int page, int size, String model) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.searchProductEntitiesByModelContainingIgnoreCase(model, pageable);
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
    public void addInventory(ProductEntity save,Integer amount,String token){
        InventoryDto inventoryDto = new InventoryDto(save.getId(),amount);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        token=token.substring(7);
        httpHeaders.setBearerAuth(token);
        HttpEntity<InventoryDto> entity=new HttpEntity<>(inventoryDto,httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(URI.create(inventoryServiceUrl + "/add"),
                HttpMethod.POST, entity, String.class);
        String body = exchange.getBody();
    }
}
